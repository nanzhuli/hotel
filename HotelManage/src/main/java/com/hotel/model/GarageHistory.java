package com.hotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="garagehistory")
public class GarageHistory
{
	@Id
	private int garageno;
	private String type;
	private Timestamp starttime;
	private Timestamp endtime;
	private String brand;
	private int price;

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

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price)
	{
		this.price=price;
	}

	@Override
	public String toString()
	{
		return "GarageHistory{"+
				"garageno="+garageno+
				", type='"+type+'\''+
				", starttime="+starttime+
				", endtime="+endtime+
				", brand='"+brand+'\''+
				", price="+price+'}';
	}
}
