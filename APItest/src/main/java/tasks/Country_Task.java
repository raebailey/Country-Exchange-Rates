package tasks;

import java.util.ArrayList;
import java.util.TimerTask;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import enums.MessageTypes;
import events.CountryEvent;
import events.CountryListener;
import models.Country;
import models.DatabaseModel;
import models.apinotifications.ApiNotification;
import sample.APItest;
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
					Country country = new Country(obj.get("alpha2Code").toString(), obj.get("name").toString(), currencyCode, lng,
							lat, imageUrl);
					country.addListener(new CountryListener() {

						@Override
						public void handleEvent(CountryEvent event) {
							panel.addMessage(event.getNotification());
							
						}
						
					});
					country.save();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			panel.addMessage(new ApiNotification(e.getMessage(), APItest.localTime(), MessageTypes.ERROR,null));
		}
		
		for(Country country : rejectCountries) {
			country.addListener(new CountryListener() {

				@Override
				public void handleEvent(CountryEvent event) {
					if(event.getAction()=="Reject") {
						panel.addMessage(event.getNotification());
					}
					
				}
				
			});
			country.reject();
		}
	}

}
