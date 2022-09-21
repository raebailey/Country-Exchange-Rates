package components.buttons;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Button extends JButton {
	private int round = 8;
	private ButtonStyles bstyles = ButtonStyles.PRIMARY;
	private ButtonColor btnColor = new ButtonColor(ButtonStyles.PRIMARY);
	private ButtonAnimation animationHover;
	private ButtonAnimation animationPress;

	/**
	 * 
	 */
	private static final long serialVersionUID = -1210895524175195087L;

	public Button() {
		defaultInit();
		
	}
	
	public Button(ButtonStyles style) {
		this.bstyles = style;
		this.btnColor = new ButtonColor(style);
		defaultInit();
		
	}
	
	private void defaultInit() {
		setContentAreaFilled(false);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setText("Button");
		setFocusable(false);
		initAnimation();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent me) {
				animationHover.start(btnColor.getBackgroundHover(), getStyle().getBackgroundHover());
			}

			@Override
			public void mouseExited(MouseEvent me) {
				animationHover.start(btnColor.getBackgroundHover(), getStyle().getBackground());
			}

			@Override
			public void mousePressed(MouseEvent me) {
				animationPress.start(btnColor.getBackground(), getStyle().getBackgroundPress());
			}

			@Override
			public void mouseReleased(MouseEvent me) {
				animationPress.start(btnColor.getBackground(), getStyle().getBackground());
			}
		});
		
		
	}
	
	@Override
	protected void paintComponent(Graphics graphic) {
		Graphics2D g2 = (Graphics2D) graphic.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int x = 0;
		int y = 0;
		int width = getWidth();
		int height = getHeight();
		Area area = new Area(new RoundRectangle2D.Double(x, y, width, height, round, round));
		g2.setColor(btnColor.getBackground());
		g2.fill(area);
		g2.setColor(btnColor.getBackgroundHover());
		g2.fill(area);
		g2.dispose();
		super.paintComponent(graphic);
	}
	
	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
		repaint();
	}
	
	public ButtonStyles getStyle() {
		return bstyles;
	}

	protected void setStyle(ButtonStyles style) {
		if (this.bstyles != style) {
			this.bstyles = style;
			animationHover.stop();
			animationPress.stop();
			btnColor.changeStyle(style);
			setForeground(bstyles.getForeground());
		}
	}
	
	protected void initAnimation() {
		animationHover = new ButtonAnimation(300, btnColor, "backgroundHover");
		animationHover.addTarget(new TimingTargetAdapter() {
			@Override
			public void timingEvent(float fraction) {
				repaint();
			}
		});
		animationPress = new ButtonAnimation(200, btnColor, "background");
		animationPress.addTarget(new TimingTargetAdapter() {
			@Override
			public void timingEvent(float fraction) {
				repaint();
			}
		});
	}

}
