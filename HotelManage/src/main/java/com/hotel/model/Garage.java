package com.hotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="garage")
public class Garage
{
	private int garagePricePreHour=2;
	private int garagePricePreDay=18;

	public int getGaragePricePreHour()
	{
		return garagePricePreHour;
	}

	public void setGaragePricePreHour(int garagePricePreHour)
	{
		this.garagePricePreHour=garagePricePreHour;
	}

	public int getGaragePricePreDay()
	{
		return garagePricePreDay;
	}

	public void setGaragePricePreDay(int garagePricePreDay)
	{
		this.garagePricePreDay=garagePricePreDay;
	}

	@Id
	private int garageno;
	private String type;
	private Timestamp starttime;
	private Timestamp endtime;
	private String brand;

	public int getGarageno()
	{
		return garageno;
	}

	public void setGarageno(int garageno)
	{
		this.garageno=garageno;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type=type;
	}

	public Timestamp getStarttime()
	{
		return starttime;
	}

	public void setStarttime(Timestamp starttime)
	{
		this.starttime=starttime;
	}

	public Timestamp getEndtime()
	{
		return endtime;
	}

	public void setEndtime(Timestamp endtime)
	{
		this.endtime=endtime;
	}

	public String getBrand()
	{
		return brand;
	}

	public void setBrand(String brand)
	{
		this.brand=brand;
	}

	@Override
	public String toString()
	{
		return "Garage{"+
				"garageno="+garageno+
				", type='"+type+'\''+
				", starttime="+starttime+
				", endtime="+endtime+
				", brand='"+brand+'\''+'}';
	}
}
