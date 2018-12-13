package com.hotel.util;

public class GarageUtil
{

	private static int garagePricePreHour=2;
	private static int garagePricePreDay=18;

	public static int getGaragePricePreHour()
	{
		return garagePricePreHour;
	}

	public void setGaragePricePreHour(int garagePricePreHour)
	{
		GarageUtil.garagePricePreHour=garagePricePreHour;
	}

	public int getGaragePricePreDay()
	{
		return garagePricePreDay;
	}

	public void setGaragePricePreDay(int garagePricePreDay)
	{
		GarageUtil.garagePricePreDay=garagePricePreDay;
	}
}
