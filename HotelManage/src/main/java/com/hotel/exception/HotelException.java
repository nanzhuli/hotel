package com.hotel.exception;

public class HotelException extends RuntimeException
{
	private int code;

	public HotelException(int code,String message)
	{
		super(message);
		this.code=code;
	}

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code=code;
	}
}
