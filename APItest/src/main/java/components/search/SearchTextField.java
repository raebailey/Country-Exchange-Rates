package components.search;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import models.Country;
import tasks.SearchTask;

public class SearchTextField extends JTextField implements EventTextField {
	private Color backgroundColor = new Color(14, 14, 14);
	private Color animationColor = new Color(14, 14, 255);
	private Icon searchIcon;
	private Icon closeIcon;
	private Icon loadingIcon;
	private String hintText = "Search Term...";
	private boolean show;
	private double location = -1;
	private EventCallBack callBack;
	private SearchTask thread;
	private final Animator animator;
	private List<String> itemSuggestion = new ArrayList<>();

	public SearchTextField(SearchTask thread) {
		this.thread = thread;
		setBackground(new Color(255, 255, 255, 0));
		setOpaque(false);
		setBorder(new EmptyBorder(10, 10, 10, 50));
		setSelectionColor(new Color(0, 43, 255));
		searchIcon = new ImageIcon(getClass().getResource("/images/searchIcon.png"));
		closeIcon = new ImageIcon(getClass().getResource("/images/closeIcon.png"));
		loadingIcon = new ImageIcon(getClass().getResource("/images/loadingIcon.gif"));

		// check if cursor is over mouse
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent event) {
				if (checkMouseOver(event.getPoint())) {
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				} else {
					setCursor(new Cursor(Cursor.TEXT_CURSOR));
				}
			}
		});

		// mouse click event listener

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent ev) {
				if (SwingUtilities.isLeftMouseButton(ev) && checkMouseOver(ev.getPoint())) {

					if (!animator.isRunning()) {
						if (show) {
							setEditable(true);
							show = false;
							location = 0;
							animator.start();
							onCancel();
						} else {
							if (!getText().trim().isEmpty()) {
								setEditable(false);
								show = true;
								location = getWidth();
								animator.start();
								onPressed(callBack);
							}

						}
					}
				}
			}
		});

		callBack = new EventCallBack() {
			@Override
			public void done() {
				setEditable(true);
				show = false;
				location = 0;
				if (animator.isRunning()) {
					animator.cancel();
				}
				animator.start();
			}
		};
		TimingTarget target = new TimingTargetAdapter() {
			@Override
			public void timingEvent(float fraction) {
				double width = getWidth();
				if (show) {
					location = width * (1f - fraction);
				} else {
					location = width * fraction;
				}
				repaint();
			}
		};
		animator = new Animator(300, target);
		animator.setResolution(0);
		animator.setAcceleration(0.5f);
		animator.setDeceleration(0.5f);
		AutoCompleteDecorator.decorate(this,itemSuggestion,false);
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2 = (Graphics2D) graphics;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setColor(backgroundColor);
		g2.fillRoundRect(0, 0, width, height, height, height);
		super.paintComponent(graphics);

		// for button
		int margin = 5;
		int buttonSize = height - margin * 2;
		GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 255, 255), width, 0, animationColor);
		g2.setPaint(gradient);
		g2.fillOval(width - height + 3, margin, buttonSize, buttonSize);

		// Create Animation when click button
		if (location > -1) {
			Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, height, height));
			area.intersect(
					new Area(new RoundRectangle2D.Double(location, 0, width - location, height, height, height)));
			g2.fill(area);
			// Create Loading icon
			int iconSize = loadingIcon.getIconHeight();
			// Create Alpha
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getAlpha()));
			g2.drawImage(((ImageIcon) loadingIcon).getImage(), (int) (location + 5), (height - iconSize) / 2, this);
		}

		// for Icon
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)); // Set to default
		int imageMargin = 5;
		int imageSize = buttonSize - imageMargin * 2;
		Image image;
		if (show) {
			image = ((ImageIcon) closeIcon).getImage();
		} else {
			image = ((ImageIcon) searchIcon).getImage();
		}
		g2.drawImage(image, width - height + imageMargin + 3, margin + imageMargin, imageSize, imageSize, null);
		g2.dispose();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (getText().trim().length() == 0) {
			int h = getHeight();
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			Insets ins = getInsets();
			FontMetrics fm = g.getFontMetrics();
			int c0 = getBackground().getRGB();
			int c1 = getForeground().getRGB();
			int m = 0xfefefefe;
			int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
			g.setColor(new Color(c2, true));
			g.drawString(hintText, ins.left, h / 2 + fm.getAscent() / 2 - 2);
		}
	}

	public Color getAnimationColor() {
		return animationColor;
	}

	public void setAnimationColor(Color animationColor) {
		this.animationColor = animationColor;
	}

	private float getAlpha() {
		float width = getWidth() / 2;
		float alpha = ((float) location) / (-width);
		alpha += 1;
		if (alpha < 0) {
			alpha = 0;
		}
		if (alpha > 1) {
			alpha = 1;
		}
		return alpha;
	}

	private boolean checkMouseOver(Point mouse) {
		int width = getWidth();
		int height = getHeight();
		int buttonMargin = 5;
		int buttonSize = height - buttonMargin * 2;
		Point point = new Point(width - height + 3, buttonMargin);
		Ellipse2D.Double circle = new Ellipse2D.Double(point.x, point.y, buttonSize, buttonSize);
		return circle.contains(mouse);
	}

	/**
	 * Sets what the search prompt will say
	 * 
	 * @param text The prompt
	 */
	public void setHintText(String text) {
		this.hintText = text;
	}
	
	// Make this an abstract method 
	public void setSuggestionItems(Country[] countries) {
		for(Country country: countries) {
			addSuggestionItem(country.getName());
		}
		
	}
	
	private void addSuggestionItem(String word) {
		itemSuggestion.add(word);
	}
	
	public List<String> getItemSuggestion(){
		return itemSuggestion;
	}

	@Override
	public void setBackground(Color color) {
		this.backgroundColor = color;
	}

	/**
	 * Set the color of the animation
	 * 
	 * @param color The desired color of the animation
	 */
	public void setAnimatorColor(Color color) {
		this.animationColor = color;
	}

	@Override
	public void onPressed(EventCallBack call) {
		String value = this.getText().trim();
		thread.setSearchTerm(value);
		thread.run();
		call.done();

	}

	@Override
	public void onCancel() {
		// TODO Auto-generated method stub

	}

}
