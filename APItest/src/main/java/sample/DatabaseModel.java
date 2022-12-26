package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import models.Country;
import models.Currency;
import models.Rate;

public class DatabaseModel {
	private Connection con;

	/**
	 * Establishes a connection with database
	 * @return Connection object
	 */
	private Connection getConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:Exchange Rates.db");
			// con.setAutoCommit(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;
	}
	
	/**
	 * Gets all countries
	 * @return An array of countries
	 */
	public Country[] getCountries() {
		if (con == null) {
			con = getConnection();
		}
		ArrayList<Country> countries = new ArrayList<Country>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT Country.*,Currency.name as curr_name,Currency.symbol from Country INNER JOIN Currency on Country.currency_code = Currency.currency_code;");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String country_code = rs.getString("country_code");
				String name = rs.getString("name");
				String currency_code = rs.getString("currency_code");
				float longitude = rs.getFloat("longitude");
				float latitude = rs.getFloat("latitude");
				String image = rs.getString("image");
				boolean visible = rs.getBoolean("isactive");
				Country country = new Country(country_code, name, currency_code, longitude, latitude, image,visible,currency_code,rs.getString("curr_name"),rs.getString("symbol"));
				countries.add(country);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = null;
		}
		return countries.toArray(new Country[countries.size()]);

	}
	
	/**
	 * Gets a specific country using the country code
	 * @param code The code for a specific country
	 * @return A country object
	 */
	public Country fetchCountry(String code) {
		PreparedStatement stmt;
		Country country = null;

		if (con == null) {
			con = getConnection();
		}

		try {
			stmt = con.prepareStatement("SELECT Country.*,Currency.name as curr_name,Currency.symbol from Country INNER JOIN Currency on Country.currency_code = Currency.currency_code where country_code = ?");
			stmt.setString(1, code);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String country_code = rs.getString("country_code");
				String name = rs.getString("name");
				String currency_code = rs.getString("currency_code");
				float longitude = rs.getFloat("longitude");
				float latitude = rs.getFloat("latitude");
				String image = rs.getString("image");
				boolean visible = rs.getBoolean("isactive");
				country = new Country(country_code, name, currency_code, longitude, latitude, image,visible,currency_code,rs.getString("curr_name"),rs.getString("symbol"));
			}
			rs.close();
			stmt.close();
			con.close();
			return country;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con = null;
		}

		return country;
	}

	/**
	 * Gets all the rates for a specific country
	 * @param code The code for a specific country 
	 * @return An array of Rates
	 */
	public Rate[] fetchRates(String code) {
		ArrayList<Rate> rateList = new ArrayList<Rate>();
		PreparedStatement stmt;

		if (con == null) {
			con = getConnection();
		}
		try {
			stmt = con.prepareStatement("SELECT * FROM Rates where currency_code = ?");
			stmt.setString(1, code);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String currency_code = rs.getString("currency_code");
				Double rate = rs.getDouble("rate");
				String date = rs.getString("last_updated");
				rateList.add(new Rate(currency_code, rate, date));
			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con = null;
		}

		return rateList.toArray(new Rate[rateList.size()]);
	}

	/**
	 * Inserts a country to Country table
	 * @param code The code of a specific country
	 * @param name The name of the country 
	 * @param currency_code The currency code of that country
	 * @param longitude The longitudinal coordinate of country
	 * @param latitude The lattitudinal coordinate of country
	 * @param image The image url of country flag
	 */
	public void insertCountry(String code, String name, String currency_code, Double longitude, Double latitude,String image) {
		if (con == null) {
			con = getConnection();
		}
		try {
			PreparedStatement stmts = con.prepareStatement("INSERT INTO Country VALUES(?,?,?,?,?,?);");
			stmts.setString(1, code);
			stmts.setString(2, name);
			stmts.setString(3, currency_code);
			stmts.setDouble(4, latitude);
			stmts.setDouble(5, longitude);
			stmts.setString(6, image);
			stmts.execute();
			stmts.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con = null;
		}

	}

	/**
	 * Inserts rate into Rate table
	 * @param key The currency code of a country
	 * @param value The rate value
	 * @param last_update The time and date that the rate was retrieved
	 */
	public void insertRate(String key,Double value, String last_update) {
		if (con == null) {
			con = getConnection();
		}
		try {
			PreparedStatement stmt = con.prepareStatement("INSERT INTO Rates VALUES(?,?,?)");
			stmt.setString(1, key);
			stmt.setDouble(2, value);
			stmt.setString(3, last_update);
			stmt.execute();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con = null;
		}

	}
	
	/**
	 * Inserts currency in Currency table
	 * @param currency_code The code used to represent a particular country
	 * @param name The name of the currency
	 * @param symbol The symbol used to represent currency
	 */
	public void insertCurrency(String currency_code,String name, String symbol) {
		if (con == null) {
			con = getConnection();
		}
		try {
			PreparedStatement stmt = con.prepareStatement("INSERT INTO Currency VALUES(?,?,?)");
			stmt.setString(1, currency_code);
			stmt.setString(2, name);
			stmt.setString(3, symbol);
			stmt.execute();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con = null;
		}

	}
	
	/**
	 * Gets Currency information for a specific country
	 * @param code The currency code for a specific country 
	 * @return A Currency object
	 */
	public Currency fetchCurrency(String code) {
		PreparedStatement stmt;
		Currency currency = null;

		if (con == null) {
			con = getConnection();
		}
		try {
			stmt = con.prepareStatement("SELECT * FROM Currency where currency_code = ?");
			stmt.setString(1, code);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String currency_code = rs.getString("currency_code");
				String name = rs.getString("name");
				String symbol = rs.getString("symbol");
				currency = new Currency(currency_code, name, symbol);
			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//con = null;
		}

		return currency;
	}

	/**
	 * Checks to see if a rate exist for a particular country on a specific date and time
	 * @param date The date and time of the rate
	 * @param code The country code of which the rate matches
	 * @return boolean 
	 */
	public boolean rateExist(String date,String code) {
		boolean exist = false;
		if (con == null) {
			con = getConnection();
		}

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Rates where last_updated = ? AND currency_code = ?");
			stmt.setString(1, date);
			stmt.setString(2, code);
			ResultSet rs = stmt.executeQuery();

			exist = rs.next();
			
			
			rs.close();
			stmt.close();
			con.close();

			return exist;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = null;
		}
		return exist;

	}

	/**
	 * Checks if a country exists
	 * @param code The code for a country
	 * @return boolean 
	 */
	public boolean countryExist(String code) {
		boolean exist = false;
		if (con == null) {
			con = getConnection();
		}

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Country where country_code = ?");
			stmt.setString(1, code);
			ResultSet rs = stmt.executeQuery();

			exist = rs.next();
			rs.close();
			stmt.close();
			con.close();

			return exist;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = null;
		}
		return exist;

	}
	
	/**
	 * Checks if a currency already exists
	 * @param code The code for a currency
	 * @return boolean
	 */
	public boolean currencyExist(String code) {
		boolean exist = false;
		if (con == null) {
			con = getConnection();
		}

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Currency where currency_code = ?");
			stmt.setString(1, code);
			ResultSet rs = stmt.executeQuery();

			exist = rs.next();
			rs.close();
			stmt.close();
			con.close();

			return exist;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = null;
		}
		return exist;

	}

	/**
	 * Gets the next execution for fetching rates
	 * @return Date object
	 */
	public Date getRunTime() {
		Date date = null;
		if (con == null) {
			con = getConnection();
		}
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT next_execution FROM NextUpdate");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(rs.getString("next_execution"));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con = null;
		}
		return date;
	}

	/**
	 * Updates the next run time to fetch rates from API
	 * @param nextRun The date and time for the next execution
	 */
	public void updateRunTime(String nextRun) {
		if (con == null) {
			con = getConnection();
		}
		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE NextUpdate set next_execution = ?");
			stmt.setString(1, nextRun);
			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con = null;
		}
	}

	/**
	 * Inserts the next runtime in the NextUpdate table
	 * @param id The id of the currency code 
	 * @param time The date and time of the next runtime
	 */
	public void insertRunTime(String id, String time) {
		if (con == null) {
			con = getConnection();
		}
		try {
			PreparedStatement stmt = con.prepareStatement("INSERT INTO NextUpdate VALUES (?,?)");
			stmt.setString(1, id);
			stmt.setString(2, time);
			stmt.execute();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con = null;
		}
	}

	/**
	 * Checks to see if run time for a specific currency exists
	 * @param code The currency code 
	 * @return boolean
	 */
	public boolean runTimeExist(String code) {
		boolean exist = false;
		if (con == null) {
			con = getConnection();
		}

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM NextUpdate where id = ?");
			stmt.setString(1, code);
			ResultSet rs = stmt.executeQuery();

			exist = rs.next();
			rs.close();
			stmt.close();
			con.close();

			return exist;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = null;
		}
		return exist;

	}
/**
 * Updates a specific country in database
 * @param country The country object that was updated
 */
	public void updateCountry(Country country) {
		if (con == null) {
			con = getConnection();
		}
		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE Country SET isactive = ? WHERE country_code = ?;");
			stmt.setInt(1, (country.isVisible())?1:0);
			stmt.setString(2, country.getCountryCode());
			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con = null;
		}
		
	}

}
