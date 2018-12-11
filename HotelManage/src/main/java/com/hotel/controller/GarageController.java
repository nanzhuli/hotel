package com.hotel.controller;

import com.hotel.model.Garage;
import com.hotel.service.GarageService;
import com.hotel.model.result;
import com.hotel.other.resultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GarageController
{
	@Autowired
	GarageService garageService;

	/**
	 *
	 * @return 返回车库列表
	 */
	@RequestMapping("/garage/getall")
	public result<List<Garage>> garageList()
	{
		return resultReturn.success(garageService.findAll());
	}

	/**
	 *
	 * @param brand 车牌
	 * @return 返回查询到的车库
	 */
	@RequestMapping("/garage/getonebybrand/{brand}")
	public result garageFindByBrand(@PathVariable("brand") String brand)
	{
		Garage garage=garageService.findByBrand(brand);
		return resultReturn.success(garage);
	}
}
