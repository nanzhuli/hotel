package com.hotel.controller;

import com.hotel.model.OrderHistory;
import com.hotel.service.OrderHistoryService;
import com.hotel.model.result;
import com.hotel.other.resultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderHistoryController
{
	@Autowired
	OrderHistoryService orderHistoryService;

	/**
	 * 返回订单历史集合
	 * @return
	 */
	@RequestMapping("/orderhistory/getall")
	public result<List<OrderHistory>> orderHistoryList()
	{
		return resultReturn.success(orderHistoryService.findAll());
	}

	@RequestMapping("/orderhistory/getone/{orderno}")
	public result orderHistorySearchOne(@PathVariable("orderno") int orderNo)
	{
		OrderHistory orderHistory=orderHistoryService.findByOrderNo(orderNo);

	}
}
