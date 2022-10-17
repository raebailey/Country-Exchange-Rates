package sample;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import tasks.Country_Task;
import tasks.UpdateRate_Task;

public class APItest {
	/**
	 * Gets the local time in 24 hour format.
	 * @return String
	 */
	public static String localTime() {
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
		 String time = dtf.format(LocalDateTime.now()).toString();
		return time;
	}
	
	/**
	 * Splits date time string into two parts date and time.
	 * @param datetime The date time string.  
	 * @return String[]
	 */
	public static String[] dateAndtime(String datetime) {
		String split[] = datetime.split(" ", 2);
		return split;
	}

}
//85436026
//85402647