package com.hotel.service;

import com.hotel.exception.ExceptionType;
import com.hotel.exception.HotelException;
import com.hotel.model.Order;
import com.hotel.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService
{
	@Autowired
	OrderRepository orderrepository;

	public List<Order> findAll()
	{
		return orderrepository.findAll();
	}

	public Order findById(int o) throws HotelException
	{
		Order order=orderrepository.findById(o).orElse(null);
		if(order==null)
		{
			throw new HotelException(ExceptionType.ORDER_FIND_BY_ID_ERROR.getCode(),
					ExceptionType.ORDER_FIND_BY_ID_ERROR.getMsg());
		}
		else
		{
			return order;
		}
	}

	public Order save(Order r)
	{
		return orderrepository.save(r);
	}

	public void delete(Order r)
	{
		orderrepository.delete(r);
	}

}
