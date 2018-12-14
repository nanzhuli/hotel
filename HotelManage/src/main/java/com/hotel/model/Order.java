package com.hotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="r_order")
public class Order
{
	@Id
	private String orderno;
	private int roomcount;
	private int price;
	private String name;
	private String id;
	private String phone;
	private Timestamp starttime;
	private Timestamp endtime;
	private int ismenber;
	private int isenter;

	public Order()
	{
	}

	public Order(String orderno,int roomcount,int price,String name,String id,String phone,Timestamp starttime,
				 Timestamp endtime,int ismenber,int isenter)
	{
		this.orderno=orderno;
		this.roomcount=roomcount;
		this.price=price;
		this.name=name;
		this.id=id;
		this.phone=phone;
		this.starttime=starttime;
		this.endtime=endtime;
		this.ismenber=ismenber;
		this.isenter=isenter;
	}

	public Order(Order order)
	{
		this.orderno=order.getOrderno();
		this.roomcount=order.getRoomcount();
		this.price=order.getPrice();
		this.name=order.getName();
		this.id=order.getId();
		this.phone=order.getPhone();
		this.starttime=order.getStarttime();
		this.endtime=order.getEndtime();
		this.ismenber=order.getIsmenber();
		this.isenter=order.getIsenter();
	}

	public String getOrderno()
	{
		return orderno;
	}

	public void setOrderno(String orderno)
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

	public int getIsmenber()
	{
		return ismenber;
	}

	public void setIsmenber(int ismenber)
	{
		this.ismenber=ismenber;
	}

	public int getIsenter()
	{
		return isenter;
	}

	public void setIsenter(int isenter)
	{
		this.isenter=isenter;
	}

	@Override
	public String toString()
	{
		return "order{"+"orderno='"+orderno+'\''+", roomcount="+roomcount+", price="+price+", name='"+name+'\''+", id='"+id+'\''+", phone='"+phone+'\''+", starttime="+starttime+", endtime="+endtime+", ismenber='"+ismenber+'\''+", isenter='"+isenter+'\''+'}';
	}
}
