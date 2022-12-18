package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import enums.MessageTypes;
import events.CountryEvent;
import events.CountryListener;
import models.apinotifications.ApiNotification;
import sample.APItest;
import sample.DatabaseModel;
import ui.ApiUI;

public class Country {
	private String countryCode;
	private String name;
	private String currencyCode;
	private double longitude;
	private double latitude;
	private String imageUrl;
	private Rate[] rate;
	private boolean visible;
	private DatabaseModel model;

	private ArrayList<CountryListener> listeners = new ArrayList<CountryListener>();

	public Country(String countryCode, String name, String currency, double longitude, double latitude, String imageUrl,
			boolean visible) {
		this.countryCode = countryCode;
		this.name = name;
		this.currencyCode = currency;
		this.longitude = longitude;
		this.latitude = latitude;
		this.imageUrl = imageUrl;
		this.visible = visible;
		this.model = new DatabaseModel();
	}

	public Country() {
		this.countryCode = "";
		this.name = "";
		this.rate = new Rate[0];
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rate[] getRate() {
		return rate;
	}

	public void setRate(Rate[] rate) {
		this.rate = rate;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public synchronized void addListener(CountryListener listener) {
		listeners.add(listener);
	}

	public synchronized void removeListener(CountryListener listener) {
		listeners.remove(listener);
	}

	public void save() {
		model.insertCountry(countryCode, name, currencyCode, longitude, latitude, imageUrl);
		String message = String.format("%s added.", name);
		processCountryEvent(new CountryEvent(this, "Save",
				new ApiNotification(message, APItest.localTime(), MessageTypes.NEWCOUNTRY, imageUrl)));

	}

	public void update(Country country) {
		System.out.println("Updated: "+country);
		model.updateCountry(country);
		String message = String.format("%s updated.", name);
		processCountryEvent(new CountryEvent(this, "Update",
				new ApiNotification(message, APItest.localTime(), MessageTypes.UPDATECOUNTRY, imageUrl)));

	}

	public void reject() {
		String message = String.format("Countries rejected: %s", name);
		processCountryEvent(new CountryEvent(this, "Reject",
				new ApiNotification(message, APItest.localTime(), MessageTypes.REJECT, imageUrl)));
	}

	private void processCountryEvent(CountryEvent countryEvent) {
		ArrayList<CountryListener> tempList;

		synchronized (this) {
			if (listeners.size() == 0)
				return;
			tempList = (ArrayList<CountryListener>) listeners.clone();
		}

		for (CountryListener listener : tempList) {
			listener.handleEvent(countryEvent);
		}
	}

	@Override
	public String toString() {
		return "Country [countryCode=" + countryCode + ", name=" + name + ", currencyCode=" + currencyCode
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", imageUrl=" + imageUrl + ", rate="
				+ Arrays.toString(rate) + ", visible=" + visible + "]";
	}

}
