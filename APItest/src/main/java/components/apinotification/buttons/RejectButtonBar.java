package components.apinotification.buttons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import components.CustomButton;
import models.apinotifications.ApiNotification;

public class RejectButtonBar extends JPanel {

	/**
	 * Create the panel.
	 */
	public RejectButtonBar(ApiNotification notif) {
		setOpaque(false);
		setPreferredSize(new Dimension(328, 40));
		setBorder(null);
		CustomButton viewAllBtn = new CustomButton();
		viewAllBtn.setPreferredSize(new Dimension(100, 30));
		viewAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(notif);
			}
		});
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		viewAllBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		viewAllBtn.setBorder(null);
		viewAllBtn.setForeground(Color.WHITE);
		viewAllBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
		viewAllBtn.setRound(5);
		viewAllBtn.setText("View All");
		viewAllBtn.setSize(100, 30);
		add(viewAllBtn);
		setVisible(true);

	}

}
