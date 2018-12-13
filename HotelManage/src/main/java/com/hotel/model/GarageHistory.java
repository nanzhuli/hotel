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
	private int garagehistoryno;
	private int garageid;
	private int type;
	private Timestamp starttime;
	private Timestamp endtime;
	private String brand;
	private int price;

	public int getGaragehistoryno()
	{
		return garagehistoryno;
	}

	public void setGaragehistoryno(int garagehistoryno)
	{
		this.garagehistoryno=garagehistoryno;
	}

	public int getGarageid()
	{
		return garageid;
	}

	public void setGarageid(int garageid)
	{
		this.garageid=garageid;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
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
				"garagehistoryno="+garagehistoryno+
				", garageid="+garageid+
				", type='"+type+'\''+
				", starttime="+starttime+
				", endtime="+endtime+
				", brand='"+brand+'\''+
				", price="+price+'}';
	}
}
