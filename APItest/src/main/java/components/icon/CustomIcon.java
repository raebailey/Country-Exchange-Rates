package components.icon;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class CustomIcon extends JLabel {
	private Icon visibleIcon;
	private Icon invisibleIcon;
	private boolean show;
	private ActionInterface action;

	public CustomIcon() {
		visibleIcon = new ImageIcon(getClass().getResource("/images/visible.png"));
		invisibleIcon = new ImageIcon(getClass().getResource("/images/invisible.png"));
		show = true;
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
					if (show) {
						show = false;
						repaint();
					} else {
						show = true;
						repaint();
					}
					action.action();
					

				}
			}
		});

	}
	
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		Graphics2D g2 = (Graphics2D) graphics;
		super.paintComponent(graphics);
		int width = getWidth();
		int height = getHeight();
		g2.setColor(new Color(14,14,14));
		g2.fillOval(0,0, getWidth(), getHeight());

		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		Image image;
		if (show) {
			image = ((ImageIcon) visibleIcon).getImage();
		} else {
			image = ((ImageIcon) invisibleIcon).getImage();
		}
		g2.drawImage(image, 0,0, width, height, null);
		
		g2.dispose();

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	private boolean checkMouseOver(Point mouse) {
		int width = getWidth();
		int height = getHeight();
		int buttonx = 0;
		int buttony = 0;
		Point point = new Point(buttonx, buttony);
		Ellipse2D.Double circle = new Ellipse2D.Double(point.x, point.y, width, height);
		return circle.contains(mouse);
	}
	
	public void addActionInterface(ActionInterface actionInterface) {
		action = actionInterface;
	}

}
