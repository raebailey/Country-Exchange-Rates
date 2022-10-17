package events;

import java.util.EventObject;

import models.Country;
import models.apinotifications.ApiNotification;

public class CountryEvent extends EventObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String action;
	private ApiNotification notification;
	public CountryEvent(Country source,String action,ApiNotification notification) {
		super(source);
		this.action = action;
		this.notification = notification;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public ApiNotification getNotification() {
		return notification;
	}
	public void setNotification(ApiNotification notification) {
		this.notification = notification;
	}
	
	

}
