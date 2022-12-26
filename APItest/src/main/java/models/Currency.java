package models;

public class Currency {
	private String currency_code;
	private String name;
	private String symbol;
	
	public Currency(String currency_code,String name,String symbol) {
		this.currency_code = currency_code;
		this.name = name;
		this.symbol = symbol;		
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "Currency [currency_code=" + currency_code + ", name=" + name + ", symbol=" + symbol + "]";
	}
	
	
}
