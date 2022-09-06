package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import models.ApiNotification;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.Cursor;

public class Cards extends JPanel {
	private ArrayList<ApiNotification> notifs;
	private JLabel datelabl;
	private JLabel messagelbl;
	private JLabel titlelbl;
	private CustomLabel counterlbl;
	private JPanel panel_2;

	/**
	 * Create the panel.
	 */
	public Cards(ApiNotification notif) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setMaximumSize(new Dimension(328, 100));
		notifs = new ArrayList<>();
		notifs.add(notif);
		init();

	}

	public JLabel getDatelabl() {
		return datelabl;
	}

	public void setDatelabl(JLabel datelabl) {
		this.datelabl = datelabl;
	}

	public JLabel getMessagelbl() {
		return messagelbl;
	}

	public void setMessagelbl(JLabel messagelbl) {
		this.messagelbl = messagelbl;
	}

	public JLabel getTitlelbl() {
		return titlelbl;
	}

	public void setTitlelbl(JLabel titlelbl) {
		this.titlelbl = titlelbl;
	}

	public CustomLabel getCounterlbl() {
		return counterlbl;
	}

	public void setCounterlbl(CustomLabel counterlbl) {
		this.counterlbl = counterlbl;
	}

	public ApiNotification getNotif() {
		return notifs.get(notifs.size() - 1);
	}

	public ArrayList<ApiNotification> getNotifs() {
		return notifs;
	}

	public void setNotifs(ArrayList<ApiNotification> notifs) {
		this.notifs = notifs;
	}

	private void init() {
		ApiNotification notif = getNotif();
		setPreferredSize(new Dimension(328, 100));
		setLayout(new BorderLayout(0, 0));
		setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 150, 255)));
		setBackground(new Color(15, 15, 15));

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		add(panel, BorderLayout.CENTER);

		messagelbl = new JLabel(notif.getMessage());
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addContainerGap().addComponent(messagelbl).addContainerGap(231, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addContainerGap().addComponent(messagelbl).addContainerGap(45, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 25));
		panel_1.setOpaque(false);
		add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(null);

		titlelbl = new JLabel(notif.getType().getTitle());
		titlelbl.setBounds(5, 5, 146, 19);
		titlelbl.setFont(new Font("SansSerif", Font.BOLD, 14));
		Image image = notif.getType().getPath();
		if (image != null) {
			titlelbl.setIcon(new ImageIcon(image));
		}
		panel_1.add(titlelbl);

		datelabl = new JLabel(notif.getLastexec());
		datelabl.setBounds(270, 7, 55, 14);
		panel_1.add(datelabl);

		panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setPreferredSize(new Dimension(30, 10));
		add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(null);

		counterlbl = new CustomLabel();
		counterlbl.setFont(new Font("SansSerif", Font.PLAIN, 13));
		counterlbl.setHorizontalTextPosition(SwingConstants.CENTER);
		counterlbl.setHorizontalAlignment(SwingConstants.CENTER);
		counterlbl.setBounds(5, 25, 24, 24);
		counterlbl.setPreferredSize(new Dimension(20, 20));
		counterlbl.setRound(100);
		counterlbl.setText(String.valueOf(notifs.size()));
		counterlbl.setVisible(false);

		if (notifs.size() > 1) {
			counterlbl.setVisible(true);
		}
		panel_2.add(counterlbl);
	}

	public void refresh() {
		ApiNotification notif = getNotif();
		Image image = notif.getType().getPath();
		if (image != null) {
			titlelbl.setIcon(new ImageIcon(image));
		}
		getTitlelbl().setText(notif.getType().getTitle());
		getDatelabl().setText(notif.getLastexec());
		getMessagelbl().setText(notif.getMessage());
		if (notifs.size() > 1) {
			getCounterlbl().setVisible(true);
			if (notifs.size() > 99) {
				getCounterlbl().setText("99+");
			} else {
				getCounterlbl().setText(String.valueOf(notifs.size()));
			}
		}

	}
}
