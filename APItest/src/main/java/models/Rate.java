package models;

public class Rate {
	private String countryCode;
	private Double rateVal;
	private String date;

	public Rate(String countryCode, Double rateVal, String date) {
		this.countryCode = countryCode;
		this.rateVal = rateVal;
		this.date = date;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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

	@Override
	public String toString() {
		return "Rate [countryCode=" + countryCode + ", rateVal=" + rateVal + ", date=" + date + "]";
	}

}
