package components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

import models.ApiNotification;
import tasks.ImageLoader_Task;

public class CardsItem extends RoundPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7890176442346377395L;
	private JLabel messagelbl;
	private JLabel lblNewLabel_1;
	private boolean expanded = false;

	/**
	 * Create the panel.
	 */
	public CardsItem(ApiNotification notif) {
		setOpaque(true);
		setMinimumSize(new Dimension(0, 0));
		setMaximumSize(new Dimension(320, 37));
		setLayout(new BorderLayout(0, 0));
//		setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 150, 255)));
		setBackground(new Color(245, 245, 245, 30));
//		setBackground(new Color(0,0,0,1));
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(37, 28));
		panel.setPreferredSize(new Dimension(37, 24));
		panel.setOpaque(false);
		add(panel, BorderLayout.WEST);

		JLabel lblNewLabel = new JLabel("");
		Image img = readImage("/images/fail.png");
		String imagePath = notif.getImage();
		if (imagePath != null) {
			JLabel[] label = { lblNewLabel };
			String[] urls = { imagePath };
			new ImageLoader_Task(label, urls, 24, 24).start();
		} else {
			img = img.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
			lblNewLabel.setIcon(new ImageIcon(img));
		}
		FlowLayout fl_panel = new FlowLayout(FlowLayout.CENTER, 10, 5);
		fl_panel.setAlignOnBaseline(true);
		panel.setLayout(fl_panel);

		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setPreferredSize(new Dimension(27, 24));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignOnBaseline(true);
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel_1, BorderLayout.CENTER);

		messagelbl = new JLabel(notif.getMessage());
		messagelbl.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		messagelbl.setMinimumSize(new Dimension(103, 18));
		messagelbl.setMaximumSize(new Dimension(103, 18));
		panel_1.add(messagelbl);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setVgap(10);
		flowLayout_1.setHgap(10);
		panel_2.setPreferredSize(new Dimension(37, 24));
		panel_2.setOpaque(false);
		add(panel_2, BorderLayout.EAST);

		lblNewLabel_1 = new JLabel("");
		Image expandimage = readImage("/images/expand.png");
		expandimage = expandimage.getScaledInstance(12, 12, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(expandimage));
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					if (expanded) {
						lblNewLabel_1.setIcon(new ImageIcon(
								readImage("/images/collapse.png").getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
						expanded = false;
					}else {
						lblNewLabel_1.setIcon(new ImageIcon(
								readImage("/images/expand.png").getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
						expanded = true;
					}
					lblNewLabel_1.repaint();
					lblNewLabel_1.validate();
				}
			}
		});
		panel_2.add(lblNewLabel_1);

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
