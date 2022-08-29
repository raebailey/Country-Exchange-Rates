package models;

public class Rate {
	private String currencyCode;
	private Double rateVal;
	private String date;
	private long id;

	public Rate(String currencyCode, Double rateVal, String date) {
		this.currencyCode = currencyCode;
		this.rateVal = rateVal;
		this.date = date;
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
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Override
	public String toString() {
		return "Rate [currencyCode=" + currencyCode + ", rateVal=" + rateVal + ", date=" + date + ", id=" + id + "]";
	}

}
