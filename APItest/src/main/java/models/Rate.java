package models;

import java.util.ArrayList;

import enums.MessageTypes;
import events.RateEvent;
import events.RateListener;
import models.apinotifications.ApiNotification;
import sample.APItest;
import sample.DatabaseModel;

public class Rate {
	private String currencyCode;
	private Double rateVal;
	private String date;
	private DatabaseModel model;
	private ArrayList<RateListener> listeners = new ArrayList<RateListener>();

	public Rate(String currencyCode, Double rateVal, String date) {
		this.currencyCode = currencyCode;
		this.rateVal = rateVal;
		this.date = date;
		this.model = new DatabaseModel();
	}

	public Double getRateVal() {
		return rateVal;
	}

	public void setRateVal(Double rateVal) {
		this.rateVal = rateVal;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	public synchronized void addListener(RateListener listener) {
		listeners.add(listener);
	}

	public synchronized void removeListener(RateListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * Inserts rate into database and triggers the event handler method
	 */
	public void save() {
		model.insertRate(currencyCode, rateVal, date);
		String message = String.format("1 USD to %s at $%s.", currencyCode,rateVal);
		processCountryEvent(new RateEvent(this, "Save", new ApiNotification(message, APItest.localTime(), MessageTypes.NEWRATE, null)));
	}
	/**
	 * Triggers the event handler method if there was no update of Rate
	 */
	public void noUpdate() {
		String message = String.format("%s is up to date.",currencyCode);
		processCountryEvent(new RateEvent(this, "No Update", new ApiNotification(message, APItest.localTime(), MessageTypes.NOUPDATE, null)));
	}
	
	/**
	 * Executes all RateListener handle event method
	 * @param countryEvent
	 */
	private void processCountryEvent(RateEvent countryEvent) {
		ArrayList<RateListener> tempList;

		synchronized (this) {
			if (listeners.size() == 0)
				return;
			tempList = (ArrayList<RateListener>)listeners.clone();
		}

		for (RateListener listener : tempList) {
			listener.handleEvent(countryEvent);
		}
	}


	@Override
	public String toString() {
		return "Rate [currencyCode=" + currencyCode + ", rateVal=" + rateVal + ", date=" + date +  "]";
	}

}
