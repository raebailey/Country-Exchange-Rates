package components.apinotification.buttons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import components.CustomButton;
import models.apinotifications.ApiNotification;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class RejectButtonBar extends JPanel {

	/**
	 * Create the panel.
	 */
	public RejectButtonBar(final ArrayList<ApiNotification> notifications) {
		setOpaque(false);
		setPreferredSize(new Dimension(328, 40));
		setBorder(null);
		CustomButton viewAllBtn = new CustomButton();
		viewAllBtn.setPreferredSize(new Dimension(100, 30));
		viewAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(notifications);
			}
		});
		viewAllBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		viewAllBtn.setBorder(null);
		viewAllBtn.setForeground(Color.WHITE);
		viewAllBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
		viewAllBtn.setRound(5);
		viewAllBtn.setText("View All");
		viewAllBtn.setSize(100, 30);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(114)
					.addComponent(viewAllBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(viewAllBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		setLayout(groupLayout);
		setVisible(true);

	}

}
