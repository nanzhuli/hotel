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
	private int orderNo;
	private int roomCount;
	private int price;
	private String name;
	private String id;
	private String phone;
	private Timestamp startTime;
	private Timestamp endTime;
	private String isMember;

	public int getOrderNo()
	{
		return orderNo;
	}

	public void setOrderNo(int orderNo)
	{
		this.orderNo=orderNo;
	}

	public int getRoomCount()
	{
		return roomCount;
	}

	public void setRoomCount(int roomCount)
	{
		this.roomCount=roomCount;
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

	public String getMember()
	{
		return isMember;
	}

	public void setMember(String member)
	{
		isMember=member;
	}

	@Override
	public String toString()
	{
		return "orderhistory{"+
				"orderno="+orderNo+
				",roomCount="+roomCount+
				",price="+price+
				",name='"+name+"'"+
				",id='"+id+"'"+
				",phone='"+phone+"'"+
				",starttime="+startTime+
				",endtime="+endTime+
				",ismember='"+isMember+"'}";
	}
}
