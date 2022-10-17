package models.apinotifications;

import enums.MessageTypes;

public class NoUpdateNotification extends ApiNotification implements INoUpdateNotificationAction{

	public NoUpdateNotification(String message, String lastexec, MessageTypes type, String image) {
		super(message, lastexec, type, image);
		// TODO Auto-generated constructor stub
	}

}
