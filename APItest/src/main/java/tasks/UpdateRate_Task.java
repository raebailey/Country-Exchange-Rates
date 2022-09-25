package tasks;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.JSONObject;

import enums.MessageTypes;
import events.RateEvent;
import events.RateListener;
import models.Rate;
import sample.APItest;
import sample.DatabaseModel;
import sample.Service;
import ui.ApiUI;

public class UpdateRate_Task extends TimerTask {
	private ApiUI panel;

	public UpdateRate_Task() {

	}
	

	public ApiUI getPanel() {
		return panel;
	}

	public void setFrame(ApiUI panel) {
		this.panel = panel;
	}

	@Override
	public void run() {
		System.out.println("Started");
		LocalTime myObj = LocalTime.now();

		System.out.println("\n\n<<<<Ran at " + myObj + ">>>>\n\n");
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
				int numCountries = 0;
				currencyObj.keySet().stream().forEach(key -> {
					boolean exist = models.currencyExist(key.toString());
					boolean rateExist = models.rateExist(date, key.toString());
					
					Rate rate = new Rate(key.toString(), Double.valueOf(currencyObj.get(key).toString()), date);
					rate.addListener(new RateListener() {

						@Override
						public void handleEvent(RateEvent event) {
							panel.addMessage(event.getNotification().getMessage(), event.getNotification().getLastexec(), event.getNotification().getType(),event.getNotification().getImage());
						}
						
					});
					if (exist == true && rateExist != true) {
						rate.save();
						String  message = String.format("1 USD to %s at $%s.", key.toString(),currencyObj.get(key));
						
						System.out.println(currencyObj.get(key) + "\n");
						if (models.runTimeExist(key.toString())) {
							models.updateRunTime(nextdate);
						} else {
							models.insertRunTime(key.toString(), nextdate);
						}
					} else {
						rate.noUpdate();
					}
				});
				if (ratesObj.get("result") == "error") {
					String error = (String) ratesObj.get("error-type");
					System.out.println("error is : " + error);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			panel.addMessage(e.getMessage(), APItest.localTime(), MessageTypes.ERROR,null);
		}

	}

}
