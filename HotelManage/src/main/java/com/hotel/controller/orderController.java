package com.hotel.controller;

import com.hotel.model.*;
import com.hotel.other.resultReturn;
import com.hotel.service.orderService;
import com.hotel.service.orderroomService;
import com.hotel.service.roomService;
import com.hotel.service.roomidService;
import com.hotel.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class orderController {
    @Autowired
    orderService orderservice;

    @ Autowired
    orderroomService orderroomservice;

    @Autowired
    roomidService roomidservice;

    @Autowired
    roomService roomservice;

    @Autowired
    OrderHistoryService orderHistoryService;
    /**
     * order表有查 更新 删除操作，更新可以更新name/id/phone/isenter 更改phone后需要去核对会员信息
     * 删除操作则是当isenter=N时才可以
     *
     * orderroom可以查看和更改，更改操作仅可以对brand车牌号进行更改
     *
     * roomid表有查看 更改操作，更改操作可以更改name/id/roomno
     * 注意的是roomno更改时应该可以下拉当前可选roomno 逻辑是先便利roomid表查找没有在其中的roomno提供给前台做选择
     * 选择之后核对并修改price
     *
     * 还有就是订单页面还有结算功能 当ismember=Y时且endtime在当天才可以阶段，逻辑是检测后删除然后转到历史中去
    * */

    //order表的查
    @RequestMapping("/order/orderlist")
    public Result<List<Order>> orderList() {
        return resultReturn.success(orderservice.findAll());
    }

    //order找一个
    @RequestMapping("/order/searchOne/{orderno}")
    public Result orderSearchOne(@PathVariable("orderno") int orderno) {
        Order r = orderservice.findById(orderno);
        if(r == null) {
            return resultReturn.error(1,"it's not exist, you can't delete!");
        }
        else {
            return resultReturn.success(r);
        }
    }

    //order表更新
    @RequestMapping("/order/update/{orderno}")
    public Result orderUpdate(@PathVariable("orderno") int orderno,@RequestParam("name") String name,
                              @RequestParam("id") String id,@RequestParam("phone") String phone,
                              @RequestParam("isenter") int isenter) {
        Order o = orderservice.findByOrderno(orderno);
        if(o==null)
            return resultReturn.error(1,"cant't find orderno!");
        o.setName(name);
        o.setId(id);
        o.setPhone(phone);
        o.setIsenter(isenter);
        return resultReturn.success(orderservice.save(o));
    }

    //order表删除
    @RequestMapping("/order/delete/{orderno}")
    public Result orderDelete(@PathVariable("orderno") int orderno) {
        //需要先查询是否存在
        Order o = orderservice.findByOrderno(orderno);
        if(o == null) {
            return resultReturn.error(1,"it's not exist, you can't delete!");
        }
        else {
            orderservice.delete(o);
            return resultReturn.success(o);
        }
    }

    //orderroom表查看
    @RequestMapping("/order/orderroom/{orderno}")
    public Result<List<orderroom>> orderroomList(@PathVariable("orderno") int orderno) {
        return resultReturn.success(orderroomservice.findAll(orderno));
    }

    //orderroom找一个
    @RequestMapping("/order/orderroom/orderroomSearchOne/{orno}")
    public Result orderroomSearchOne(@PathVariable("orno") int orno) {
        orderroom r = orderroomservice.findById(orno);
        if(r == null) {
            return resultReturn.error(1,"it's not exist, you can't delete!");
        }
        else {
            return resultReturn.success(r);
        }
    }

    //orderroom表更新
    @RequestMapping("/order/orderroom/update/{orno}")
    public Result orderroomUpdate(@PathVariable("orno")int orno,@RequestParam("brand")String brand) {
        orderroom o = orderroomservice.findOne(orno);
        if(o==null)
            return resultReturn.error(1,"cant't find rino");
        o.setBrand(brand);
        return resultReturn.success(orderroomservice.save(o));
    }

    //roomid表查看
    @RequestMapping("/order/orderroom/roomid/list/{roomno}")
    public Result<List<roomid>> roomidList(@PathVariable("roomno") int roomno) {
        return resultReturn.success(roomidservice.findAll(roomno));
    }

    //roomid找一个
    @RequestMapping("/order/orderroom/roomid/roomidSearchOne/{rino}")
    public Result roomidSearchOne(@PathVariable("rino") int rino) {
        roomid r = roomidservice.findById(rino);
        if(r == null) {
            return resultReturn.error(1,"it's not exist, you can't delete!");
        }
        else {
            return resultReturn.success(r);
        }
    }

    //roomid表修改
    /**在进入更改操作之前需要先获取可以更换的roomno号 调用 函数*/
    @RequestMapping("/order/orderroom/roomid/update/{rino}")
    public Result roomidUpdate(@PathVariable("rino")int rino,@RequestParam("roomnoAfter")int roomnoAfter,
                               @RequestParam("roomnoBefore")int roomnoBefore,@RequestParam("orderno")int orderno,
                               @RequestParam("name") String name,@RequestParam("id") String id) {
        roomid ri = roomidservice.findByRino(rino);
        ri.setRoomno(roomnoAfter);
        ri.setId(id);
        ri.setName(name);
        //修改price
        Order o = orderservice.findByOrderno(orderno);
        room ra = roomservice.findById(roomnoAfter);
        room rb = roomservice.findById(roomnoBefore);
        o.setPrice(o.getPrice()+ra.getPrice()-rb.getPrice());
        orderservice.save(o);
        return resultReturn.success(roomidservice.save(ri));
    }

    //获取不在roomid中的roomno
    @RequestMapping("/order/orderroom/roomid/getEmptyRoomno")
    public Result<List<Integer>> getEmptyRoomno() {
        List<roomid> ri = roomidservice.findAllWithoutparam();
        List<Integer> q = new ArrayList<>(ri.size());
        for(int i=0; i<ri.size(); i++) {
            q.add(ri.get(i).getRoomno());
        }
        List<room> r = roomservice.getEmpty(q);
        List<Integer> sum = new ArrayList<Integer>(r.size());
        for(int i=0; i<r.size(); i++) {
            sum.add(r.get(i).getRoomno());
        }
        return resultReturn.success(sum);
    }

    //结算功能
    @RequestMapping("/order/settle/{orderno}")
    public Result orderSettle(@PathVariable("orderno")int orderno) {
        Order o = orderservice.findByOrderno(orderno);
        if(o.getIsenter()==1){
            orderservice.delete(o);

            return new OrderHistoryController(orderHistoryService).orderHistoryInsert(o);
        }
        else {
            return resultReturn.error(1,"还未入住");
        }

    }
}
