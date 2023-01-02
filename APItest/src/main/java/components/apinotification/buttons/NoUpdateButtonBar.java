package components.apinotification.buttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import components.CustomButton;
import models.apinotifications.ApiNotification;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class NoUpdateButtonBar extends JPanel {

	/**
	 * Create the panel.
	 */
	public NoUpdateButtonBar(final ArrayList<ApiNotification> notifications) {
		setBackground(new Color(240, 240, 240));
		setOpaque(false);
		setPreferredSize(new Dimension(328, 40));
		setBorder(null);
		
		CustomButton settingsBtn = new CustomButton();
		settingsBtn.setPreferredSize(new Dimension(100, 30));
		settingsBtn.setBorder(null);
		settingsBtn.setForeground(Color.WHITE);
		settingsBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
		settingsBtn.setRound(5);
		settingsBtn.setText("Settings");
		
		CustomButton viewAllBtn = new CustomButton();
		viewAllBtn.setPreferredSize(new Dimension(100, 30));
		viewAllBtn.setBorder(null);
		viewAllBtn.setForeground(Color.WHITE);
		viewAllBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
		viewAllBtn.setRound(5);
		viewAllBtn.setText("View All");
		viewAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(notifications);
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(settingsBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(64)
					.addComponent(viewAllBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(settingsBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(viewAllBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		setLayout(groupLayout);
		
		setVisible(true);

	}

}
