package components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class CardsItem extends RoundPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7890176442346377395L;
	private JLabel messagelbl;

	/**
	 * Create the panel.
	 */
	public CardsItem(String message) {
		setOpaque(true);
		setMinimumSize(new Dimension(0, 0));
		setMaximumSize(new Dimension(320, 37));
		setLayout(new BorderLayout(0, 0));
//		setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 150, 255)));
		setBackground(new Color(245, 245, 245, 30));
//		setBackground(new Color(0,0,0,1));
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(28, 28));
		panel.setPreferredSize(new Dimension(24, 24));
		panel.setOpaque(false);
		add(panel, BorderLayout.WEST);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setLocation(0, 0);
		Image img = readImage("/images/fail.png");
		if(img!=null) {
			img = img.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
			lblNewLabel.setIcon(new ImageIcon(img));
		}
		panel.setLayout(null);
		
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setPreferredSize(new Dimension(24, 24));
		lblNewLabel.setSize(new Dimension(24, 24));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignOnBaseline(true);
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel_1, BorderLayout.CENTER);
		
		messagelbl = new JLabel(message);
		messagelbl.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		messagelbl.setMinimumSize(new Dimension(103, 18));
		messagelbl.setMaximumSize(new Dimension(103, 18));
		panel_1.add(messagelbl);

	}
	
	
	
	public JLabel getMessagelbl() {
		return messagelbl;
	}



	public void setMessagelbl(JLabel messagelbl) {
		this.messagelbl = messagelbl;
	}



	private Image readImage(String path) {
		Image img = null;
		try {
			img = ImageIO.read(getClass().getResource(path));
			return img;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}

}
