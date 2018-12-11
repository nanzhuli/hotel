package com.hotel.controller;

import com.hotel.exception.ExceptionType;
import com.hotel.exception.HotelException;
import com.hotel.model.OrderHistory;
import com.hotel.model.Result;
import com.hotel.other.resultReturn;
import com.hotel.service.OrderHistoryService;
import com.hotel.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class OrderHistoryController
{
	private final OrderHistoryService orderHistoryService;

	@Autowired
	public OrderHistoryController(OrderHistoryService orderHistoryService)
	{
		this.orderHistoryService=orderHistoryService;
	}

	/**
	 * @return 返回订单历史集合
	 */
	@RequestMapping("/orderhistory/getall")
	public Result<List<OrderHistory>> orderHistoryList()
	{
		return resultReturn.success(orderHistoryService.findAll());
	}

	/**
	 * @param orderNo 订单历史的主键
	 * @return 返回订单历史查询结果（根据主键查询）
	 * @throws HotelException 因主键不存在而抛出的异常
	 */
	@RequestMapping("/orderhistory/getone/{orderno}")
	public Result orderHistorySearchOne(@PathVariable("orderno") int orderNo) throws HotelException
	{
		OrderHistory orderHistory=orderHistoryService.findByOrderNo(orderNo);
		if(orderHistory==null)
		{
			throw new HotelException(ExceptionType.ORDER_HISTORY_FINDING_ERROR.getCode(),ExceptionType.ORDER_HISTORY_FINDING_ERROR.getMsg());
		}
		else
		{
			return resultReturn.success(orderHistory);
		}
	}

	/**
	 *
	 * @param roomCount 房间数量
	 * @param price 订单价格
	 * @param name 下单人姓名
	 * @param id 下单人身份证
	 * @param phone 下单人电话号码
	 * @param startTime 下单日期
	 * @param endTime 结算日期
	 * @param isMember 下单人是否是会员
	 * @return 返回插入新的订单记录的结果
	 */
	@RequestMapping("/orderhistory/insert")
	public Result orderHistoryInsert(@RequestParam("roomcount") int roomCount,
									 @RequestParam("price") int price,
									 @RequestParam("name") String name,
									 @RequestParam("id") String id,
									 @RequestParam("phone") String phone,
									 @RequestParam("starttime") Timestamp startTime,
									 @RequestParam("endtime") Timestamp endTime,
									 @RequestParam("ismember") String isMember)
	{
		OrderHistory orderHistory=saveOrderHistory(roomCount,price,name,id,
												   phone,startTime,endTime,isMember);
		return resultReturn.success(orderHistoryService.save(orderHistory));
	}

	/**
	 *
	 * @param roomCount 房间数量
	 * @param price 订单价格
	 * @param name 下单人姓名
	 * @param id 下单人身份证
	 * @param phone 下单人电话号码
	 * @param startTime 下单日期
	 * @param endTime 结算日期
	 * @param isMember 下单人是否是会员
	 * @return 根据参数返回订单历史记录
	 */
	private OrderHistory saveOrderHistory(int roomCount,int price,String name,
										   String id,String phone,Timestamp startTime,
										   Timestamp endTime,String isMember)
	{
		OrderHistory orderHistory=new OrderHistory();
		orderHistory.setEndTime(endTime);
		orderHistory.setId(id);
		orderHistory.setMember(isMember);
		orderHistory.setName(name);
		orderHistory.setPhone(phone);
		orderHistory.setPrice(price);
		orderHistory.setRoomCount(roomCount);
		orderHistory.setStartTime(startTime);

		return orderHistory;
	}

	/**
	 *
	 * @param order 结算完成的订单
	 * @return 返回对应的订单记录（作为历史记录）
	 */
	private OrderHistory saveOrderHistory(Order order)
	{
		OrderHistory orderHistory=new OrderHistory();
		orderHistory.setEndTime(order.getEndtime());
		orderHistory.setId(order.getId());
		/*orderHistory.setMember(order.getIsmenber());*/
		orderHistory.setName(order.getName());
		orderHistory.setPhone(order.getPhone());
		orderHistory.setPrice(order.getPrice());
		orderHistory.setRoomCount(order.getRoomcount());
		orderHistory.setStartTime(order.getStarttime());

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

		return resultReturn.success(orderHistoryService.save(orderHistory));
	}
}
