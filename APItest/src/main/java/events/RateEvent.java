package events;

import java.util.EventObject;

import models.Rate;
import models.apinotifications.ApiNotification;

public class RateEvent extends EventObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String action;
	private ApiNotification notification;
	public RateEvent(Rate source,String action,ApiNotification notification) {
		super(source);
		this.action = action;
		this.notification = notification;
	}
	/**
	 * Returns the action that took place for Rate object
	 * 	 @return String
	 */
	public String getAction() {
		return action;
	}
	
	/**
	 * Sets the action for rate object
	 * @param action The name of the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	
	/**
	 * Returns notification 
	 * @return ApiNotification
	 */
	public ApiNotification getNotification() {
		return notification;
	}
	
	/**
	 * Sets ApiNotification object
	 * @param notification
	 */
	public void setNotification(ApiNotification notification) {
		this.notification = notification;
	}
}
