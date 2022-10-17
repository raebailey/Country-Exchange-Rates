package models.apinotifications;

import enums.MessageTypes;

public class ExceptionNotification extends ApiNotification implements IExceptionNotificationAction{

	public ExceptionNotification(String message, String lastexec, MessageTypes type, String image) {
		super(message, lastexec, type, image);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void viewAll() {
		// TODO Auto-generated method stub
		
	}

}
