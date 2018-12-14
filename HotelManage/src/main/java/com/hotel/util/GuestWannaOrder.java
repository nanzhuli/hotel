package com.hotel.util;

public class GuestWannaOrder extends Guest
{
	private String phone;

	public GuestWannaOrder(String name,String id,String phone)
	{
		super(name,id);
		this.phone=phone;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone=phone;
	}

	@Override
	public String toString()
	{
		return "GuestGetOrdered{"+
				"name='"+this.getName()+'\''+
				",id='"+this.getId()+'\''+
				",phone='"+phone+'\''+'}';
	}
}
