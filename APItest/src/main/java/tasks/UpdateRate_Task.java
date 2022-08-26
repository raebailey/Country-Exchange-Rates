package tasks;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.TimerTask;

import org.json.simple.JSONObject;

import sample.DatabaseModel;
import sample.Service;

public class UpdateRate_Task extends TimerTask {

	@Override
	public void run() {
		System.out.println("Started");
		LocalTime myObj = LocalTime.now();

		System.out.println("\n\n<<<<Ran at " + myObj + ">>>>\n\n");
		// TODO Auto-generated method stub
		try {
			JSONObject ratesObj = Service.getRate();
			if (ratesObj.get("result").toString().equalsIgnoreCase("success")) {
				long lastupdateObj = (long) ratesObj.get("time_last_update_unix");
				long nextupdateTime = (long) ratesObj.get("time_next_update_unix");
				String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(lastupdateObj * 1000));
				String nextdate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(nextupdateTime * 1000));
				JSONObject currencyObj = (JSONObject) ratesObj.get("conversion_rates");
				System.out.println("Update Time:(unix) " + lastupdateObj + " (date time) " + date);
				System.out.println("Next Update Time:(unix) " + nextupdateTime + " (date time) " + nextdate);
				DatabaseModel models = new DatabaseModel();
				currencyObj.keySet().stream().forEach(key -> {
					if (key.toString().equals("JMD")) {
						boolean exist = models.countryExist(key.toString());
						boolean rateExist = models.rateExist(key.toString(),
								Double.valueOf(currencyObj.get(key).toString()));
						if (exist == true && rateExist != true) {
							models.insertRate(key.toString(), currencyObj.get(key).toString(), date);
							System.out.println(currencyObj.get(key) + "\n");
							if (models.runTimeExist(key.toString())) {
								models.updateRunTime(nextdate);
							} else {
								models.insertRunTime(key.toString(), nextdate);
							}
						}else {
							System.out.println("ERROR");
						}
					}
//					System.out.println("Key: " + key + " Value: " + currencyObj.get(key));
				});
			} else {
				if (ratesObj.get("result") == "error") {
					String error = (String) ratesObj.get("error-type");
					System.out.println("error is : " + error);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
