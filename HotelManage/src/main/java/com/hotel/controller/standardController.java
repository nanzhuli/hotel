package com.hotel.controller;

import com.hotel.model.standard;
import com.hotel.service.standardService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@RestController

public class standardController {
    @Resource
    standardService standservice;
    //standard stand;

    @RequestMapping("/standard")
    public List<standard> translateStandard() {
        return standservice.getAll();
    }
}
