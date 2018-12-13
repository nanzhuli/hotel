package com.hotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="member")
public class Member
{
	@Id
	private int memberno;
	private String phone;
	private String name;
	private String id;
	private Timestamp entertime;

	public int getMemberno()
	{
		return memberno;
	}

	public void setMemberno(int memberno)
	{
		this.memberno=memberno;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone=phone;
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

	public Timestamp getEntertime()
	{
		return entertime;
	}

	public void setEntertime(Timestamp entertime)
	{
		this.entertime=entertime;
	}

	@Override
	public String toString()
	{
		return "Member{"+
				"memberno="+memberno+
				", phone='"+phone+'\''+
				", name='"+name+'\''+
				", id='"+id+'\''+
				", entertime="+entertime+'}';
	}
}
