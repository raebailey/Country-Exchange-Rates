package sample;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import tasks.Country_Task;
import tasks.UpdateRate_Task;

public class APItest {
// https://www.datawrapper.de/ - DISPLAY THE TABLE INFORMATION 
	public static void main(String[] args) throws java.text.ParseException {
		Timer timer = new Timer();
		TimerTask task;

		task = new UpdateRate_Task();
		Date nextTime = new DatabaseModel().getRunTime();
				new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("16/08/2022 21:05:00");

		Date now = new Date();
		long difference = nextTime.getTime() - now.getTime();
		long hours = (difference / (1000 * 60 * 60)) % 24;
		long minutes = (difference / (1000 * 60)) % 60;
		long seconds = (difference / 1000) % 60;
		//24 hr :  86400000
		timer.schedule(task, now,1800000);
		System.out.println("Hours until execution time:" + difference + "\n" + hours + "  hours " + minutes
				+ " minutes " + seconds + " seconds ");

		new Timer().schedule(new Country_Task(), new Date());

	}

}
//85436026
//85402647