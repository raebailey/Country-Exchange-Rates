package models.apinotifications;

import enums.MessageTypes;

public class UpdateNotification extends ApiNotification implements IUpdateNotificationAction{

	public UpdateNotification(String message, String lastexec, MessageTypes type, String image) {
		super(message, lastexec, type, image);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ViewOne(ApiNotification notif) {
		// TODO Auto-generated method stub
		
	}

}
