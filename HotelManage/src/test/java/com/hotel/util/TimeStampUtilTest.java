package com.hotel.util;

import org.junit.Test;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import static org.junit.Assert.*;

public class TimeStampUtilTest
{

	@Test
	public void getStringArray()
	{
		/*Calendar calendar=Calendar.getInstance();
		Timestamp timestamp=new Timestamp(
				calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE),
				calendar.get(Calendar.HOUR),
				calendar.get(Calendar.MINUTE),
				calendar.get(Calendar.SECOND),
				0
				);*/
		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		System.out.println(timestamp);
		for(String str:new TimeStampUtil().getStringArray(timestamp))
		{
			System.out.println(str);
		}

		Timestamp ts1=new Timestamp(System.currentTimeMillis());
		try
		{
			Thread.sleep(100);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		Timestamp ts2=new Timestamp(System.currentTimeMillis());
		System.out.println(ts2.getTime()-ts1.getTime());
	}

	@Test
	public void getHoursFromTowTimeStamp()
	{
	}
}