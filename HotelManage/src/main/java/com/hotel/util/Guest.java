package com.hotel.util;

public class Guest
{
	private String name;
	private String id;

	public Guest(String name,String id)
	{
		this.name=name;
		this.id=id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name=name;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id=id;
	}

	@Override
	public String toString()
	{
		return "Guest{"+"name='"+name+'\''+", id='"+id+'\''+'}';
	}
}
