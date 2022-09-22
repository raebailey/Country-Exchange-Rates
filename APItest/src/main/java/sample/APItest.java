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
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern(" hh:mm a");
		 String time = dtf.format(LocalDateTime.now()).toString();
		return time;
	}

}
//85436026
//85402647