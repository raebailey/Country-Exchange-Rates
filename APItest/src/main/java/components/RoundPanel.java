package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class RoundPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8657448092213436324L;
	private int round = 15;

	public RoundPanel() {
		setOpaque(false);
		setBackground(new Color(14, 14, 14));
	}

	@Override
	public void paint(Graphics grphcs) {
		Graphics2D g2 = (Graphics2D) grphcs.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), round, round);
		g2.dispose();
		super.paint(grphcs);
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
		repaint();
	}
	
	
}
