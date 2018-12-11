package com.hotel.controller;

import com.hotel.model.Result;
import com.hotel.model.room;
import com.hotel.model.standard;
import com.hotel.util.ResultReturn;
import com.hotel.service.roomService;
import com.hotel.service.standardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomInformationController {
    @Autowired
    standardService standservice;

    @Autowired
    roomService roomservice;

    //查询房间标准列表
    @RequestMapping("/standard")
    public Result<List<standard>> standardList() {
        return ResultReturn.success(standservice.getAll());
    }

    //查询客房信息列表
    @RequestMapping("/room/roomlist")
    public Result<List<room>> roomList() {
        return ResultReturn.success(roomservice.getAll()) ;
    }

    //添加客房信息
    @PostMapping("/room/add")
    public Result roomAdd(@RequestParam("roomno") int roomno,@RequestParam("type") int type,
						  @RequestParam("price") int price,@RequestParam("ifwindow") int ifwindow,
						  @RequestParam("comment") String comment) {
        System.out.println("qian"+roomno+"   "+roomservice.findById(roomno));
        //需要做检测
        if(roomservice.findById(roomno)==null) {
            System.out.println(roomno);
            room r = saveRoom(roomno, type, price, ifwindow, comment);
            return ResultReturn.success(roomservice.save(r));
        }
        else
            return ResultReturn.error(2,"roomno already exist!");
    }

    //更新已有信息
    @RequestMapping("/room/update/{roomno}")
    public Result roomUpdate(@PathVariable("roomno") int roomno,@RequestParam("type") int type,
							 @RequestParam("price") int price,@RequestParam("ifwindow") int ifwindow,
							 @RequestParam("comment") String comment) {
        //需要先查询是否存在
        if(roomservice.findById(roomno)==null) {
            return ResultReturn.error(1,"cant't find roomno!");
        }
        else {
            room r = saveRoom(roomno, type, price, ifwindow, comment);
            return ResultReturn.success(roomservice.save(r));
        }
    }

    @RequestMapping("/room/searchOne/{roomno}")
    public Result roomSerchOne(@PathVariable("roomno") int roomno) {
        room r = roomservice.findById(roomno);
        if(r == null) {
            return ResultReturn.error(1,"it's not exist, you can't delete!");
        }
        else {
            return ResultReturn.success(r);
        }
    }


    @RequestMapping("/room/delete/{roomno}")
    public Result roomDelete(@PathVariable("roomno") int roomno) {
        //需要先查询是否存在
        room r = roomservice.findById(roomno);
        if(r == null) {
            return ResultReturn.error(1,"it's not exist, you can't delete!");
        }
        else {
            roomservice.delete(r);
            return ResultReturn.success(r);
        }
    }


    public room saveRoom(int roomno, int type, int price, int ifwindow, String comment) {
        room r = new room();
        r.setRoomno(roomno);
        r.setType(type);
        r.setPrice(price);
        r.setIfwindow(ifwindow);
        r.setComment(comment);
        return r;
    }


}
