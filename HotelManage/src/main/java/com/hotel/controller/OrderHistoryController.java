package com.hotel.controller;

import com.hotel.model.OrderHistory;
import com.hotel.model.Result;
import com.hotel.util.ResultReturn;
import com.hotel.service.OrderHistoryService;
import com.hotel.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderHistoryController
{

	@Autowired
	private OrderHistoryService orderHistoryService;

	/**
	 * @return 返回订单历史集合
	 */
	@RequestMapping("/orderhistory/getall")
	public Result<List<OrderHistory>> getAll()
	{
		return ResultReturn.success(orderHistoryService.findAll());
	}

	/**
	 *
	 * @param year 年份
	 * @param month 月份
	 * @param day 天数
	 * @return 返回对应的日订单历史
	 */
	@RequestMapping("/orderhistory/getallbyday")
	public Result<List<OrderHistory>> getAllByDay(@RequestParam("year") int year,@RequestParam("month") int month,
												  @RequestParam("day") int day)
	{
		return ResultReturn.success(orderHistoryService.findByDay(year,month,day));
	}

	/**
	 *
	 * @param year 年份
	 * @param month 月份
	 * @return 返回对应的月订单历史
	 */
	@RequestMapping("/orderhistory/getallbymonth")
	public Result<List<OrderHistory>> getAllByDay(@RequestParam("year") int year,@RequestParam("month") int month)
	{
		return ResultReturn.success(orderHistoryService.findByMonth(year,month));
	}

	/**
	 *
	 * @param year 年份
	 * @return 返回对应的年订单历史
	 */
	@RequestMapping("/orderhistory/getallbyyear")
	public Result<List<OrderHistory>> getAllByDay(@RequestParam("year") int year)
	{
		return ResultReturn.success(orderHistoryService.findByYear(year));
	}

	/**
	 *
	 * @param id 身份证
	 * @return 返回对应的订单历史
	 */
	@RequestMapping("/orderhistory/getallbyid")
	public Result<List<OrderHistory>> getAllByDay(@RequestParam("id") String id)
	{
		return ResultReturn.success(orderHistoryService.findByID(id));
	}

	/**
	 * @param order 结算完成的订单
	 * @return 返回对应的订单记录（作为历史记录）
	 */
	private OrderHistory saveOrderHistory(Order order)
	{
		OrderHistory orderHistory=new OrderHistory();
		orderHistory.setEndtime(order.getEndtime());
		orderHistory.setId(order.getId());
		orderHistory.setMember(order.getIsmenber());
		orderHistory.setName(order.getName());
		orderHistory.setPhone(order.getPhone());
		orderHistory.setPrice(order.getPrice());
		orderHistory.setRoomcount(order.getRoomcount());
		orderHistory.setStarttime(order.getStarttime());

		return orderHistory;
	}

	/**
	 * 注意！！此方法只于OrderController中调用！！
	 *
	 * @param order 结算完成的订单
	 * @return 返回插入新的订单记录的结果
	 */
	public Result orderHistoryInsert(Order order)
	{
		OrderHistory orderHistory=saveOrderHistory(order);

		return ResultReturn.success(orderHistoryService.save(orderHistory));
	}
}
