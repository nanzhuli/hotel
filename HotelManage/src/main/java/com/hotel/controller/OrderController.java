package com.hotel.controller;

import com.hotel.exception.ExceptionType;
import com.hotel.exception.HotelException;
import com.hotel.model.*;
import com.hotel.service.*;
import com.hotel.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController
{
	private final OrderService orderservice;

	private final OrderRoomService orderroomservice;

	private final RoomidService roomidservice;

	private final RoomService roomservice;

	private final OrderHistoryService orderHistoryService;

	private final FinanceService financeService;

	@Autowired
	public OrderController(OrderService orderservice,OrderRoomService orderroomservice,RoomidService roomidservice,
						   RoomService roomservice,OrderHistoryService orderHistoryService,
						   FinanceService financeService)
	{
		this.orderservice=orderservice;
		this.orderroomservice=orderroomservice;
		this.roomidservice=roomidservice;
		this.roomservice=roomservice;
		this.orderHistoryService=orderHistoryService;
		this.financeService=financeService;
	}

	/**
	 * order表有查 更新 删除操作，更新可以更新name/id/phone/isenter 更改phone后需要去核对会员信息
	 * 删除操作则是当isenter=N时才可以
	 * <p>
	 * orderroom可以查看和更改，更改操作仅可以对brand车牌号进行更改
	 * <p>
	 * roomid表有查看 更改操作，更改操作可以更改name/id/roomno
	 * 注意的是roomno更改时应该可以下拉当前可选roomno 逻辑是先便利roomid表查找没有在其中的roomno提供给前台做选择
	 * 选择之后核对并修改price
	 * <p>
	 * 还有就是订单页面还有结算功能 当ismember=Y时且endtime在当天才可以阶段，逻辑是检测后删除然后转到历史中去
	 */

	/**
	 * 查找所有订单
	 *
	 * @return 返回现有的全部订单
	 */
	@RequestMapping("/order/orderlist")
	public Result<List<Order>> orderList()
	{
		return ResultReturn.success(orderservice.findAll());
	}

	/**
	 * 根据订单号查找对应订单信息
	 *
	 * @param orderno 订单号
	 * @return 返回订单号对应的订单
	 */
	@RequestMapping("/order/searchOne/{orderno}")
	public Result<Order> orderSearchOne(@PathVariable("orderno") String orderno)
	{
		return ResultReturn.success(orderservice.findByOrderNo(orderno));
	}

	/**
	 * 更新订单信息
	 *
	 * @param orderno 订单号
	 * @param name    姓名
	 * @param id      身份证
	 * @param phone   电话号码
	 * @param isenter 是否入住
	 * @return 返回更新后的订单
	 */
	@RequestMapping("/order/update/{orderno}")
	public Result orderUpdate(@PathVariable("orderno") String orderno,@RequestParam("name") String name,
							  @RequestParam("id") String id,@RequestParam("phone") String phone,
							  @RequestParam("isenter") int isenter)
	{
		Order o=orderservice.findByOrderNo(orderno);
		o.setName(name);
		o.setId(id);
		o.setPhone(phone);
		o.setIsenter(isenter);
		return ResultReturn.success(orderservice.save(o));
	}

	/**
	 * 删除订单
	 *
	 * @param orderno 订单号
	 * @return 返回成功
	 */
	@RequestMapping("/order/delete/{orderno}")
	public Result orderDelete(@PathVariable("orderno") String orderno)
	{
		Order o=orderservice.findByOrderNo(orderno);
		orderservice.delete(o);
		return ResultReturn.success();
	}

	/**
	 * 根据订单号查找所有的订单中的所有房间
	 *
	 * @param orderno 订单号
	 * @return 返回房间列表
	 */
	@RequestMapping("/order/orderroom/{orderno}")
	public Result<List<OrderRoom>> orderroomList(@PathVariable("orderno") String orderno)
	{
		return ResultReturn.success(orderroomservice.findAll(orderno));
	}

	/**
	 * 根据订单-房间序号查找对应记录
	 *
	 * @param orno 订单-房间表的序号
	 * @return 返回对应的订单-房间信息
	 */
	@RequestMapping("/order/orderroom/orderroomSearchOne/{orno}")
	public Result<OrderRoom> orderroomSearchOne(@PathVariable("orno") int orno)
	{
		return ResultReturn.success(orderroomservice.findOne(orno));
	}

	/**
	 * OrderRoom表更新
	 *
	 * @param orno         编号
	 * @param brand        车牌号
	 * @param roomnoAfter  之前的房间号
	 * @param roomnoBefore 现在重新设定的房间号（计算价格需要）
	 * @param orderno      订单号（保存当前订单的价格需要）
	 * @return 返回保存的orderroom对象
	 */
	@RequestMapping("/order/orderroom/update/{orno}")
	public Result orderroomUpdate(@PathVariable("orno") int orno,@RequestParam("brand") String brand,
								  @RequestParam("roomnoAfter") int roomnoAfter,
								  @RequestParam("roomnoBefore") int roomnoBefore,@RequestParam("orderno") String orderno)
	{
		OrderRoom or=orderroomservice.findOne(orno);
		or.setBrand(brand);
		or.setRoomno(roomnoAfter);

		List<Roomid> ri=roomidservice.findAll(roomnoBefore);
		for (Roomid aRi : ri)
		{
			aRi.setRoomno(roomnoAfter);
		}
		roomidservice.saveAll(ri);

		Order order=orderservice.findByOrderNo(orderno);
		Room roomAfter=roomservice.findByRoom(roomnoAfter);
		Room roomBefore=roomservice.findByRoom(roomnoBefore);
		order.setPrice(order.getPrice()+roomAfter.getPrice()-roomBefore.getPrice());
		orderservice.save(order);

		return ResultReturn.success(orderroomservice.save(or));
	}

	//roomid表查看
	@RequestMapping("/order/orderroom/roomid/list/{roomno}")
	public Result<List<Roomid>> roomidList(@PathVariable("roomno") int roomno)
	{
		return ResultReturn.success(roomidservice.findAll(roomno));
	}

	/**
	 * 根据房间-身份证表的序号查找对应信息
	 *
	 * @param rino 房间-身份证表的序号
	 * @return 返回对应信息
	 */
	@RequestMapping("/order/orderroom/roomid/roomidSearchOne/{rino}")
	public Result<Roomid> roomidSearchOne(@PathVariable("rino") int rino)
	{
		return ResultReturn.success(roomidservice.findByRino(rino));
	}

	/**
	 * 房间-身份证表更新
	 *
	 * @param rino 房间-身份证表的序号
	 * @param name 姓名
	 * @param id   身份证
	 * @return 返回更改后的信息
	 */
	@RequestMapping("/order/orderroom/roomid/update/{rino}")
	public Result roomidUpdate(@PathVariable("rino") int rino,@RequestParam("name") String name,
							   @RequestParam("id") String id)
	{
		Roomid ri=roomidservice.findByRino(rino);
		ri.setId(id);
		ri.setName(name);
		return ResultReturn.success(roomidservice.save(ri));
	}

	/**
	 * 寻找未入住的房间号列表
	 *
	 * @return 返回房间号列表
	 */
	@RequestMapping("/order/orderroom/roomid/getEmptyRoomno")
	public Result<List<Integer>> getEmptyRoomno()
	{
		List<Roomid> ri=roomidservice.findAllWithoutparam();
		List<Integer> q=new ArrayList<>(ri.size());
		for (Roomid aRi : ri)
		{
			q.add(aRi.getRoomno());
		}
		List<Room> r=roomservice.getEmpty(q);
		List<Integer> sum=new ArrayList<>();
		for (Room aR : r)
		{
			sum.add(aR.getRoomno());
		}
		return ResultReturn.success(sum);
	}

	/**
	 * 结算订单
	 *
	 * @param orderno 订单号
	 * @return 返回订单结算结果
	 * @throws HotelException 抛出订单未入住异常 code: 601
	 */
	@RequestMapping("/order/settle/{orderno}")
	public Result<OrderHistory> orderSettle(@PathVariable("orderno") String orderno) throws HotelException
	{
		Order order=orderservice.findByOrderNo(orderno);
		if(order.getIsenter()==1)
		{
			Order orderTemp=new Order(order);
			orderservice.delete(order);

			return new OrderHistoryController(orderHistoryService,financeService).orderHistoryInsert(orderTemp);
		}
		else
		{
			throw new HotelException(ExceptionType.ORDER_ISENTER_ERROR.getCode(),
					ExceptionType.ORDER_ISENTER_ERROR.getMsg());
		}

	}
}
