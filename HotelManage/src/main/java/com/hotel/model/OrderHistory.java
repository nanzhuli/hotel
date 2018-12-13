package com.hotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="orderhistory")
public class OrderHistory
{
	@Id
	private int orderno;
	private int roomcount;
	private int price;
	private String name;
	private String id;
	private String phone;
	private Timestamp starttime;
	private Timestamp endtime;
	private int ismember;

	public int getOrderno()
	{
		return orderno;
	}

	public void setOrderno(int orderno)
	{
		this.orderno=orderno;
	}

	public int getRoomcount()
	{
		return roomcount;
	}

	public void setRoomcount(int roomcount)
	{
		this.roomcount=roomcount;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price)
	{
		this.price=price;
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

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone=phone;
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

	public int getMember()
	{
		return ismember;
	}

	public void setMember(int member)
	{
		ismember=member;
	}

	@Override
	public String toString()
	{
		return "orderhistory{"+
				"orderno="+orderno+
				",roomcount="+roomcount+
				",price="+price+
				",name='"+name+"'"+
				",id='"+id+"'"+
				",phone='"+phone+"'"+
				",starttime="+starttime+
				",endtime="+endtime+
				",ismember="+ismember+"}";
	}
}
