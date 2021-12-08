package com.test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestTimeSatamp {
	public static void main(String[] args)
	{
		Calendar cl=Calendar.getInstance();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str1=df.format(cl.getTime());
		String str2=df.format(cl.getTimeInMillis());
		Timestamp ts=Timestamp.valueOf(str2);
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(ts);

	}

}
