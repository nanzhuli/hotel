package com.hotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="garage")
public class Garage
{
	@Id
	private int garageNo;
	private String type;
	private Timestamp starttime;
	private Timestamp endTime;
	private String brand;

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

	public Timestamp getStarttime()
	{
		return starttime;
	}

	public void setStarttime(Timestamp starttime)
	{
		this.starttime=starttime;
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

	@Override
	public String toString()
	{
		return "Garage{"+
				"garageNo="+garageNo+
				", type='"+type+'\''+
				", starttime="+starttime+
				", endTime="+endTime+
				", brand='"+brand+'\''+'}';
	}
}
