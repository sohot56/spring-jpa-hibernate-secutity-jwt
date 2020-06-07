package com.example.demo.service.mapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeConverter {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
	public String dateToString(Date d) {
		return dateFormat.format(d);
	}
	public Date stringToDate(String s) {
		try {
			return dateFormat.parse(s);
		} catch (ParseException e) {
		}
		return null;
		
	}
}
