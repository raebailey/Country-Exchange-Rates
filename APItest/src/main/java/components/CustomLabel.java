package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JLabel;

public class CustomLabel extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int round;
	private String text;

	public CustomLabel() {
		this.text = " ";
		this.round = 0;
		setText(text);
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
		g2.setColor(new Color(35, 64, 153));
//		R:35 G:64 B:153
		g2.fill(area);
		// area.subtract(new Area(new RoundRectangle2D.Double(x + 1, y + 1, width - 2,
		// height - 2, round, round)));
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

}
