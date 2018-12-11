package com.hotel.handle;

import com.hotel.exception.HotelException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotel.model.result;
import com.hotel.other.resultReturn;
import com.hotel.exception.ExceptionType;

@ControllerAdvice
public class ExceptionHandle
{
	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public result handel(Exception e)
	{
		if(e instanceof HotelException)
		{
			HotelException hotelException=(HotelException)e;
			return resultReturn.error(
					hotelException.getCode(),
					hotelException.getMessage()
			);
		}
		else
		{
			return resultReturn.error(
					ExceptionType.UNKNOWN_ERROR.getCode(),
					ExceptionType.UNKNOWN_ERROR.getMsg()
			);
		}

	}
}