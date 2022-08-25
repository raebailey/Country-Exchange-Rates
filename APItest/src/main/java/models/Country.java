package models;

import java.util.Arrays;

public class Country {
	private String countryCode;
	private String name;
	private String currencyCode;
	private float longitude;
	private float latitude;
	private String imageUrl;
	private Rate[] rate;

	public Country(String countryCode, String name, String currency, float longitude, float latitude, String imageUrl) {
		this.countryCode = countryCode;
		this.name = name;
		this.currencyCode = currency;
		this.longitude = longitude;
		this.latitude = latitude;
		this.imageUrl = imageUrl;
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

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Country [countryCode=" + countryCode + ", name=" + name + ", currencyCode=" + currencyCode
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", imageUrl=" + imageUrl + ", rate="
				+ Arrays.toString(rate) + "]";
	}

}
