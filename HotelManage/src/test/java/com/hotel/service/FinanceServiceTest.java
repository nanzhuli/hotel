package com.hotel.service;

import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class FinanceServiceTest
{

	@Test
	public void findByDay()
	{
	}

	@Test
	public void findByMonth()
	{
	}

	@Test
	public void findByYear()
	{

		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		System.out.println(timestamp.getYear());
	}
}