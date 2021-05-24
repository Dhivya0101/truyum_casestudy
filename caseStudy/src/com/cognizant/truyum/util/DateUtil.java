package com.cognizant.truyum.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static Date convertToDate(String Date) throws ParseException{
		
		SimpleDateFormat simpledate = new SimpleDateFormat("dd/MM/yyyy");
		Date date1;
			
			date1 = simpledate.parse(Date);
			return date1;
		
	}
	public static void main(String[] args) throws ParseException {
		System.out.println("Date : "+ DateUtil.convertToDate("10/10/2000"));
	}  
}

