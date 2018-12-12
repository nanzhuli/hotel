package com.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class hotelController {
    @RequestMapping(value = "/login")
    public String hotel1(){
        return "login";
    }

    @RequestMapping(value = "/adminHotel")
    public String adminhotel(){
        return "adminHotel";
    }

    @RequestMapping(value = "/workerHotel")
    public String workerhotel(){
        return "workerHotel";
    }

    @RequestMapping(value = "/serverHotel")
    public String serverhotel(){
        return "serverHotel";
    }
}
