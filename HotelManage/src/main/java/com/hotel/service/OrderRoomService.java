package com.hotel.service;

import com.hotel.exception.ExceptionType;
import com.hotel.exception.HotelException;
import com.hotel.model.OrderRoom;
import com.hotel.repository.OrderRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderRoomService
{
	@Autowired
	OrderRoomRepository orderroomrepository;

	public List<OrderRoom> findAll(int orderno)
	{
		OrderRoom or=new OrderRoom();
		or.setOrderno(orderno);
		ExampleMatcher exampleMatcher=ExampleMatcher.matching().withMatcher("orderno",
				ExampleMatcher.GenericPropertyMatchers.contains()).withIgnorePaths("orno","roomno","brand");
		Example<OrderRoom> ex=Example.of(or,exampleMatcher);
		return orderroomrepository.findAll(ex);
	}

	public OrderRoom findOne(int orderRoomNo) throws HotelException
	{
		OrderRoom orderRoom=orderroomrepository.findById(orderRoomNo).orElse(null);

		if(orderRoom!=null)
		{
			return orderRoom;
		}
		else
		{
			throw new HotelException(ExceptionType.ORDERROOM_FIND_BY_ROOMIDNO_ERROR.getCode(),
					ExceptionType.ORDERROOM_FIND_BY_ROOMIDNO_ERROR.getMsg());
		}
	}

	public OrderRoom save(OrderRoom e)
	{
		return orderroomrepository.save(e);
	}

}
