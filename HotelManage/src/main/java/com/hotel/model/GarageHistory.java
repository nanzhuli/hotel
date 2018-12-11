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
	private int garageNo;
	private String type;
	private Timestamp startTime;
	private Timestamp endTime;
	private String brand;
	private int price;

	public int getGarageNo()
	{
		return garageNo;
	}

	public void setGarageNo(int garageNo)
	{
		this.garageNo=garageNo;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type=type;
	}

	public Timestamp getStartTime()
	{
		return startTime;
	}

	public void setStartTime(Timestamp startTime)
	{
		this.startTime=startTime;
	}

	public Timestamp getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Timestamp endTime)
	{
		this.endTime=endTime;
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
				"garageNo="+garageNo+
				", type='"+type+'\''+
				", startTime="+startTime+
				", endTime="+endTime+
				", brand='"+brand+'\''+
				", price="+price+'}';
	}
}
