package com.salestaxes.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static String getFormattedDate(Date date) {
		return new SimpleDateFormat("MMM/dd/YYYY HH:MM:ss").format(date);
	}
	
}
