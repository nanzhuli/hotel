package com.hotel.controller;

import com.hotel.model.Garage;
import com.hotel.model.GarageHistory;
import com.hotel.model.Result;
import com.hotel.service.GarageHistoryService;
import com.hotel.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class GarageHistoryController
{
	@Autowired
	GarageHistoryService garageHistoryService;

	/**
	 *
	 * @return 返回车库历史纪录列表
	 */
	@RequestMapping("/garagehistory/getall")
	public Result<List<GarageHistory>> garageHistoryList()
	{
		return ResultReturn.success(garageHistoryService.findAll());
	}

	/**
	 *
	 * @param garageId 车库(位)编号
	 * @return 返回根据车库(位)编号查询到的车库历史纪录列表
	 */
	@RequestMapping("/garagehistory/getallbyid/{garageno}")
	public Result<List<GarageHistory>> garageHistoryListById(@PathVariable("garageno") int garageId)
	{
		return ResultReturn.success(garageHistoryService.findAllById(garageId));
	}

	/**
	 *
	 * @param brand 车牌
	 * @return 返回根据车牌查询到的车库历史纪录列表
	 */
	@RequestMapping("/garagehistory/getallbybrand/{brand}")
	public Result<List<GarageHistory>> garageHistoryListByBrand(@PathVariable("brand") String brand)
	{
		return ResultReturn.success(garageHistoryService.findAllByBrand(brand));
	}


	public Result garageHistoryInsertLog(Garage garage,Timestamp endTime)
	{
		GarageHistory newGarageHistory=new GarageHistory();

		newGarageHistory.setEndtime(endTime);
		newGarageHistory.setBrand(garage.getBrand());
		newGarageHistory.setGarageid(garage.getGarageno());
		newGarageHistory.setStarttime(garage.getStarttime());
		newGarageHistory.setType(garage.getType());
		newGarageHistory.setPrice(
				new TimeStampUtil().getHoursFromTwoTimeStamp(garage.getStarttime(),endTime)
						*garage.getGaragePricePreHour()
		);

		return ResultReturn.success(garageHistoryService.save(newGarageHistory));
	}
}
