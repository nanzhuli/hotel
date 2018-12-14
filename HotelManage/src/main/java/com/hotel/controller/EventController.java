package com.hotel.controller;

import com.hotel.model.Employ;
import com.hotel.model.Event;
import com.hotel.model.Result;
import com.hotel.model.Room;
import com.hotel.util.ResultReturn;
import com.hotel.service.EmployService;
import com.hotel.service.EventService;
import com.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EventController
{
	private final EmployService employservice;

	private final EventService eventservice;

	private final RoomService roomservice;

	@Autowired
	public EventController(EmployService employservice,EventService eventservice,RoomService roomservice)
	{
		this.employservice=employservice;
		this.eventservice=eventservice;
		this.roomservice=roomservice;
	}

	//查看事务列表
	@RequestMapping("/event/allList")
	public Result<Event> eventList()
	{
		return ResultReturn.success(eventservice.findAll());
	}

	//添加新的事务
	@RequestMapping("/event/add")
	public Result evenAdd(@RequestParam("type") int type,@RequestParam("roomno") int roomno,
						  @RequestParam("comment") String comment)
	{
		Event e=new Event();
		e.setType(type);
		e.setRoomno(roomno);
		e.setComment(comment);
		//检测房间号是否存在
		java.sql.Timestamp timestamp=new java.sql.Timestamp(System.currentTimeMillis());
		e.setStarttime(timestamp);

		Room r=roomservice.findByRoom(roomno);
		if(r==null)
			return ResultReturn.error(1,"roomno is not exist");
		//需要做employno
		Employ em=employservice.eventMatch(type,timestamp);
		if(em.getEmployno()==0)
		{
			return ResultReturn.error(1,"can't match worker in that time");
		}
		else
		{
			e.setEmployno(em.getEmployno());
			return ResultReturn.success(eventservice.save(e));
		}
	}

	//更新事务
	@RequestMapping("/event/update/{eventno}")
	public Result eventUpdate(@PathVariable("eventno") int eventno,@RequestParam("type") int type,
							  @RequestParam("roomno") int roomno,@RequestParam("comment") String comment)
	{
		Event E=eventservice.findAllByEventno(eventno);
		if(E==null)
			return ResultReturn.error(1,"can't find this eventno");
		//更新时房间号确认
		E.setType(type);
		Room r=roomservice.findByRoom(roomno);
		if(r==null)
			return ResultReturn.error(1,"roomno is not exist");
		E.setRoomno(roomno);
		E.setComment(comment);
		Employ em=employservice.eventMatch(type,E.getStarttime());
		if(em.getEmployno()==0)
		{
			return ResultReturn.error(1,"can't match worker in that time");
		}
		else
		{
			E.setEmployno(em.getEmployno());
			return ResultReturn.success(eventservice.save(E));
		}
	}

	//根据eventno删除事务
	@RequestMapping("/event/delete/{eventno}")
	public Result eventDelete(@PathVariable("eventno") int eventno)
	{
		Event E=eventservice.findAllByEventno(eventno);
		if(E==null)
			return ResultReturn.error(1,"can't find this eventno");
		eventservice.delete(E);
		return ResultReturn.success(E);
	}

	@RequestMapping("/event/searchOne/{eventno}")
	public Result evenSerchOne(@PathVariable("eventno") int eventno)
	{
		Event e=eventservice.findById(eventno);
		if(e==null)
		{
			return ResultReturn.error(1,"it's not exist, you can't delete!");
		}
		else
		{
			return ResultReturn.success(e);
		}
	}

}
