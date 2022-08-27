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
import models.Rate;

public class DatabaseModel {
	private Connection con;

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
	
	// TODO Refactor database to account for possibility of more than one country having the same currency code

	public Country[] getCountries() {
		if (con == null) {
			con = getConnection();
		}
		ArrayList<Country> countries = new ArrayList<Country>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Country;");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String country_code = rs.getString("country_code");
				String name = rs.getString("name");
				String currency_code = rs.getString("currency_code");
				float longitude = rs.getFloat("longitude");
				float latitude = rs.getFloat("latitude");
				String image = rs.getString("image");
				Country country = new Country(country_code, name, currency_code, longitude, latitude, image);
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

	public Country fetchCountry(String code) {
		PreparedStatement stmt;
		Country country = null;

		if (con == null) {
			con = getConnection();
		}

		try {
			stmt = con.prepareStatement("SELECT * FROM Country where currency_code = ?");
			stmt.setString(1, code);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String country_code = rs.getString("country_code");
				String name = rs.getString("name");
				String currency_code = rs.getString("currency_code");
				float longitude = rs.getFloat("longitude");
				float latitude = rs.getFloat("latitude");
				String image = rs.getString("image");
				country = new Country(country_code, name, currency_code, longitude, latitude, image);
				// System.out.println("Code = " + country_code);
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
				String country_code = rs.getString("currency_code");
				Double rate = rs.getDouble("rate");
				String date = rs.getString("last_updated");
				rateList.add(new Rate(country_code, rate, date));
				System.out.println("Code = " + country_code);
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

	public void insertCountry(String code, String name, String currency_code, Double longitude, Double latitude,
			String image) {
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

	public void insertRate(String key, String value, String last_update) {
		if (con == null) {
			con = getConnection();
		}
		try {
			PreparedStatement stmt = con.prepareStatement("INSERT INTO Rates VALUES(?,?,?)");
			stmt.setString(1, key);
			stmt.setDouble(2, Double.valueOf(value));
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

	public boolean rateExist(String code, Double rate) {
		boolean exist = false;
		if (con == null) {
			con = getConnection();
		}

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Rates where currency_code = ? and rate = ?");
			stmt.setString(1, code);
			stmt.setDouble(2, rate);
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

	public boolean countryExist(String code) {
		boolean exist = false;
		if (con == null) {
			con = getConnection();
		}

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Country where currency_code = ?");
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

}
