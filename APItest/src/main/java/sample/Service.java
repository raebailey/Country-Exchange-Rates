package sample;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Service {
/**
 * Fetches rate information from rates api.
 * @return JSONObject
 * @throws Exception
 */
	public static JSONObject getRate() throws Exception {
		URL url = new URL("https://v6.exchangerate-api.com/v6/3b19aa14e7bde0f41741099a/latest/USD");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.connect();

		int responseCode = con.getResponseCode();

		if (responseCode != 200) {
			throw new RuntimeException("Response code" + responseCode);
		}
		StringBuilder information = new StringBuilder();
		Scanner scanner = new Scanner(url.openStream());

		while (scanner.hasNext()) {
			information.append(scanner.nextLine());
		}

		scanner.close();

		JSONParser parse = new JSONParser();

		JSONObject jsonObj = (JSONObject) parse.parse(String.valueOf(information));
		return jsonObj;

	}

	/**
	 * Fetches country information from country api.
	 * @return JSONObject
	 * @throws Exception
	 */
	public static JSONArray getCountry() throws Exception {
		URL url = new URL("https://restcountries.com/v2/all");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.connect();

		int responseCode = con.getResponseCode();

		if (responseCode != 200) {
			throw new RuntimeException("Response code" + responseCode);
		}
		StringBuilder information = new StringBuilder();
		Scanner scanner = new Scanner(url.openStream());

		while (scanner.hasNext()) {
			information.append(scanner.nextLine());
		}

		scanner.close();

		JSONParser parse = new JSONParser();
		JSONArray dataArr = (JSONArray) parse.parse(String.valueOf(information));
		return dataArr;

	}

	static long v;

	public static long getNextTime(Date nextTime) throws ParseException {

		Date today = new Date();

		long difference = nextTime.getTime() - today.getTime();
		long hours = (difference / (1000 * 60 * 60)) % 24;
		long minutes = (difference / (1000 * 60)) % 60;
		long seconds = (difference / 1000) % 60;
		System.out.println(
				"long:" + difference + "\n" + hours + "  hours " + minutes + " minutes " + seconds + " seconds ");
		return difference;
	}
//	21 104 325
//	80 397 736
}
