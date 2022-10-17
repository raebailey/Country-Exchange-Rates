package models.apinotifications;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import components.CustomButton;
import enums.MessageTypes;

public class RejectNotification extends ApiNotification implements IRejectNotificationAction{

	public RejectNotification(String message, String lastexec, MessageTypes type, String image) {
		super(message, lastexec, type, image);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CustomButton viewAll() {
		CustomButton view = new CustomButton();
		view.setText("View All");
		view.setRound(5);
		view.setForeground(Color.WHITE);
		view.setFont(new Font("SansSerif", Font.BOLD, 14));
		view.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		return view;
	}

}
