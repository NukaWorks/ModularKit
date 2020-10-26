package com.github.sunproject.org.modularframework.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {

	public static void log(String string) {
		System.out.println("[" + getTime() + "] " + string);
	}
	
	public static void err(String string) {
		System.err.println("[" + getTime() + "] " + string);
	}

	public static String getTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM HH:mm:ss");
		return sdf.format(cal.getTime());
	}

}
