package com.hotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="Finance")
public class Finance
{
	@Id
	private int financeno;
	private String type;
	private int money;
	private Timestamp time;

	public int getFinanceno()
	{
		return financeno;
	}

	public void setFinanceno(int financeno)
	{
		this.financeno=financeno;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type=type;
	}

	public int getMoney()
	{
		return money;
	}

	public void setMoney(int money)
	{
		this.money=money;
	}

	public Timestamp getTime()
	{
		return time;
	}

	public void setTime(Timestamp time)
	{
		this.time=time;
	}

	@Override
	public String toString()
	{
		return "Finance{"+
				"financeno="+financeno+
				", type='"+type+'\''+
				", money="+money+
				", time="+time+'}';
	}
}
