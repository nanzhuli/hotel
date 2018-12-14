package com.hotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orderroom")
public class OrderRoom
{
	@Id
	private int orno;
	private String orderno;
	private int roomno;
	private String brand;

	public int getOrno()
	{
		return orno;
	}

	public void setOrno(int orno)
	{
		this.orno=orno;
	}

	public String getOrderno()
	{
		return orderno;
	}

	public void setOrderno(String orderno)
	{
		this.orderno=orderno;
	}

	public int getRoomno()
	{
		return roomno;
	}

	public void setRoomno(int roomno)
	{
		this.roomno=roomno;
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
		return "OrderRoom{"+"orno="+orno+", orderno='"+orderno+'\''+", roomno="+roomno+", brand='"+brand+'\''+'}';
	}
}
