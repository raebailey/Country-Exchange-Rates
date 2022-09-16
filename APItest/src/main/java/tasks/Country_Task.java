package tasks;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.TimerTask;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import enums.MessageTypes;
import models.Country;
import sample.APItest;
import sample.DatabaseModel;
import sample.Service;
import ui.ApiUI;

public class Country_Task extends TimerTask {
	private ApiUI panel;
	private ArrayList<Country> rejectCountries = new ArrayList<>();

	public ApiUI getPanel() {
		return panel;
	}

	public void setFrame(ApiUI panel) {
		this.panel = panel;
	}

	@Override
	public void run() {
		int reject = 0;
		
		try {

			// JSONObject obj
			JSONArray json = (JSONArray) Service.getCountry();
			for (int i = 0; i < json.size(); i++) {
				JSONObject obj = (JSONObject) json.get(i);
				

				JSONArray locationArr = (JSONArray) obj.get("latlng");
				Double lng = (double) 0;
				Double lat = (double) 0;
				if (locationArr != null) {
					lng = (Double) locationArr.get(1);
					lat = (Double) locationArr.get(0);
				}

				JSONObject flag = (JSONObject) obj.get("flags");
				String imageUrl = (String) flag.get("png");
				
				JSONArray jsonArr = (JSONArray) obj.get("currencies");
				if (jsonArr == null) {
					reject += 1;
					rejectCountries.add(new Country(obj.get("alpha2Code").toString(), obj.get("name").toString(), "", lng.floatValue(),
							lat.floatValue(), imageUrl));
					continue;
				}
				JSONObject currency_code = (JSONObject) jsonArr.get(0);

				DatabaseModel model = new DatabaseModel();
				String currencyCode = currency_code.get("code").toString();
				boolean currExists = model.currencyExist(currencyCode);
				if (currExists == false) {
					String name = currency_code.get("name").toString();
					String symbol = currency_code.get("symbol").toString();
					model.insertCurrency(currencyCode, name, symbol);
				}
				boolean exists = model.countryExist(obj.get("alpha2Code").toString());
				if (exists != true) {
					model.insertCountry(obj.get("alpha2Code").toString(), obj.get("name").toString(), currencyCode, lng,
							lat, imageUrl);
					String message = obj.get("name") + " added.";
					panel.addMessage(message, APItest.localTime(), MessageTypes.NEWCOUNTRY,null);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			panel.addMessage(e.getMessage(), APItest.localTime(), MessageTypes.ERROR,null);
		}
		
		System.out.print(reject + " countries rejected\nCountries rejected: " + rejectCountries);
		for(Country country : rejectCountries) {
			String message = String.format("Countries rejected: %s",country.getName());
			panel.addMessage(message, APItest.localTime(), MessageTypes.REJECT,country.getImageUrl());
		}
		

	}

}
