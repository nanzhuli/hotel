package com.hotel.service;

import com.hotel.exception.ExceptionType;
import com.hotel.exception.HotelException;
import com.hotel.model.OrderHistory;
import com.hotel.repository.OrderHistoryRepository;
import com.hotel.util.TimeStampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderHistoryService
{
	@Autowired
	OrderHistoryRepository orderHistoryRepository;

	public List<OrderHistory> findAll()
	{
		return orderHistoryRepository.findAll();
	}

	public List<OrderHistory> findByDay(int year,int month,int day)
	{
		List<OrderHistory> orderHistoryList=new ArrayList<>();

		for (OrderHistory orderHistory : orderHistoryRepository.findAll())
		{
			if (orderHistory.getEndtime()==null)
			{
				continue;
			}
			String[] strings=new TimeStampUtil().getStringArray(orderHistory.getEndtime());
			if(strings[0].equals(Integer.toString(year)) && strings[1].equals(
					Integer.toString(month)) && strings[2].equals(Integer.toString(day)))
			{
				orderHistoryList.add(orderHistory);
			}
		}

		if(orderHistoryList.size()>0)
		{
			return orderHistoryList;
		}
		else
		{
			throw new HotelException(ExceptionType.ORDER_HISTORY_FIND_BY_DAY_ERROR.getCode(),
					ExceptionType.ORDER_HISTORY_FIND_BY_DAY_ERROR.getMsg());
		}
	}

	public List<OrderHistory> findByMonth(int year,int month)
	{
		List<OrderHistory> orderHistoryList=new ArrayList<>();

		for (OrderHistory orderHistory : orderHistoryRepository.findAll())
		{
			if (orderHistory.getEndtime()==null)
			{
				continue;
			}
			String[] strings=new TimeStampUtil().getStringArray(orderHistory.getEndtime());
			if(strings[0].equals(Integer.toString(year)) && strings[1].equals(
					Integer.toString(month)))
			{
				orderHistoryList.add(orderHistory);
			}
		}

		if(orderHistoryList.size()>0)
		{
			return orderHistoryList;
		}
		else
		{
			throw new HotelException(ExceptionType.ORDER_HISTORY_FIND_BY_MONTH_ERROR.getCode(),
					ExceptionType.ORDER_HISTORY_FIND_BY_MONTH_ERROR.getMsg());
		}
	}

	public List<OrderHistory> findByYear(int year)
	{
		List<OrderHistory> orderHistoryList=new ArrayList<>();

		for (OrderHistory orderHistory : orderHistoryRepository.findAll())
		{
			if (orderHistory.getEndtime()==null)
			{
				continue;
			}
			String[] strings=new TimeStampUtil().getStringArray(orderHistory.getEndtime());
			if(strings[0].equals(Integer.toString(year)))
			{
				orderHistoryList.add(orderHistory);
			}
		}

		if(orderHistoryList.size()>0)
		{
			return orderHistoryList;
		}
		else
		{
			throw new HotelException(ExceptionType.ORDER_HISTORY_FIND_BY_YEAR_ERROR.getCode(),
					ExceptionType.ORDER_HISTORY_FIND_BY_YEAR_ERROR.getMsg());
		}
	}

	public List<OrderHistory> findByID(String ID)
	{
		List<OrderHistory> orderHistoryList=new ArrayList<>();

		for (OrderHistory orderHistory : orderHistoryRepository.findAll())
		{
			if (orderHistory.getId()==null)
			{
				continue;
			}
			if(orderHistory.getId().equals(ID))
			{
				orderHistoryList.add(orderHistory);
			}
		}

		if(orderHistoryList.size()>0)
		{
			return orderHistoryList;
		}
		else
		{
			throw new HotelException(ExceptionType.ORDER_HISTORY_FIND_BY_ID_ERROR.getCode(),
					ExceptionType.ORDER_HISTORY_FIND_BY_ID_ERROR.getMsg());
		}
	}

	public OrderHistory save(OrderHistory orderHistory)
	{
		return orderHistoryRepository.save(orderHistory);
	}

	public void delete(OrderHistory orderHistory)
	{
		orderHistoryRepository.delete(orderHistory);
	}

}

