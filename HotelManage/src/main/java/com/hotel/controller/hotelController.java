package com.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hotelController {
    @RequestMapping(value = "/123",method = RequestMethod.GET)
    public String hotel(){
        return "hotel";
    }

}
