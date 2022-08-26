package tasks;

import java.util.ArrayList;
import java.util.TimerTask;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import sample.DatabaseModel;
import sample.Service;

public class Country_Task extends TimerTask {

	@Override
	public void run() {
		int reject = 0;
		ArrayList<String> rejectCountries = new ArrayList<String>();
		try {

			// JSONObject obj
			JSONArray json = (JSONArray) Service.getCountry();
			for (int i = 0; i < json.size(); i++) {
				JSONObject obj = (JSONObject) json.get(i);
				JSONArray jsonArr = (JSONArray) obj.get("currencies");
				if (jsonArr == null) {
					reject += 1;
					rejectCountries.add(obj.get("name").toString());
					continue;
				}
				JSONObject currency_code = (JSONObject) jsonArr.get(0);

				JSONArray locationArr = (JSONArray) obj.get("latlng");
				Double lng = (double) 0;
				Double lat = (double) 0;
				if (locationArr != null) {
					lng = (Double) locationArr.get(0);
					lat = (Double) locationArr.get(1);
				}

				JSONObject flag = (JSONObject) obj.get("flags");
				String imageUrl = (String) flag.get("png");

				DatabaseModel model = new DatabaseModel();
				boolean exists = model.countryExist(currency_code.get("code").toString());
				if (exists != true) {
					model.insertCountry(obj.get("alpha2Code").toString(), obj.get("name").toString(),
							currency_code.get("code").toString(), lng, lat, imageUrl);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.print(reject + " countries rejected\nCountries rejected: " + rejectCountries);

	}

}
