package models;

import enums.MessageTypes;

public class ApiNotification {
	private String message;
	private MessageTypes type;
	private String lastexec;
	private String image;
	
	
	public ApiNotification(String message,String lastexec,MessageTypes type,String image) {
		this.message = message;
		this.lastexec = lastexec;
		this.type = type;
		this.image = image;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageTypes getType() {
		return type;
	}

	public void setType(MessageTypes type) {
		this.type = type;
	}

	public String getLastexec() {
		return lastexec;
	}

	public void setLastexec(String lastexec) {
		this.lastexec = lastexec;
	}

	public String getImage() {
		return this.image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	
	

}
