package com.hotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="garage")
public class Garage implements Cloneable
{
	@Id
	private int garageno;
	private int type;
	private Timestamp starttime;
	private Timestamp endtime;
	private String brand;

	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}

	public Garage()
	{
	}

	public Garage(int garageno,int type,Timestamp starttime,Timestamp endtime,String brand)
	{
		this.garageno=garageno;
		this.type=type;
		this.starttime=starttime;
		this.endtime=endtime;
		this.brand=brand;
	}

	public Garage(Garage garage)
	{
		this.garageno=garage.getGarageno();
		this.type=garage.getType();
		this.starttime=garage.getStarttime();
		this.endtime=garage.getEndtime();
		this.brand=garage.getBrand();
	}

	public int getGarageno()
	{
		return garageno;
	}

	public void setGarageno(int garageno)
	{
		this.garageno=garageno;
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
