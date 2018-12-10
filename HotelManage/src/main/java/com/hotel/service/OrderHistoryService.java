package com.hotel.service;

import com.hotel.model.OrderHistory;
import com.hotel.repository.OrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public OrderHistory findByOrderNo(int orderNo)
	{
		return orderHistoryRepository.findById(orderNo).orElse(null);
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

