package events;

import java.util.EventObject;

import components.Cards;
import models.apinotifications.ApiNotification;

public class AddCardsItemEvent extends EventObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ApiNotification notification;
	
	public AddCardsItemEvent(Cards card,ApiNotification notification) {
		super(card);
		this.notification = notification;
	}

	public ApiNotification getNotification() {
		return notification;
	}

	public void setNotification(ApiNotification notification) {
		this.notification = notification;
	}
	
	

}
