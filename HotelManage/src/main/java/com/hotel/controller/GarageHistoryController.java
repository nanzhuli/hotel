package com.hotel.controller;

import com.hotel.model.Garage;
import com.hotel.model.GarageHistory;
import com.hotel.model.Result;
import com.hotel.service.GarageHistoryService;
import com.hotel.service.GarageService;
import com.hotel.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class GarageHistoryController
{
	final GarageHistoryService garageHistoryService;
	
	@Autowired
	GarageService garageService;
	
	@Autowired
	public GarageHistoryController(GarageHistoryService garageHistoryService) {this.garageHistoryService = garageHistoryService;}
	
	/**
	 * @return 返回车库历史纪录列表
	 */
	@RequestMapping("/garagehistory/getall")
	public Result<List<GarageHistory>> garageHistoryList()
	{
		return ResultReturn.success(garageHistoryService.findAll());
	}

	/**
	 * @param garageId 车库(位)编号
	 * @return 返回根据车库(位)编号查询到的车库历史纪录列表
	 */
	@RequestMapping("/garagehistory/getallbyid/{garageno}")
	public Result<List<GarageHistory>> garageHistoryListById(@PathVariable("garageno") int garageId)
	{
		return ResultReturn.success(garageHistoryService.findAllById(garageId));
	}

	/**
	 * @param brand 车牌
	 * @return 返回根据车牌查询到的车库历史纪录列表
	 */
	@RequestMapping("/garagehistory/getallbybrand/{brand}")
	public Result<List<GarageHistory>> garageHistoryListByBrand(@PathVariable("brand") String brand)
	{
		return ResultReturn.success(garageHistoryService.findAllByBrand(brand));
	}

	/**
	 * @param year  年份
	 * @param month 月份
	 * @param day   天数
	 * @return 返回日车库历史
	 */
	@RequestMapping("/garagehistory/getallbyday")
	public Result<List<GarageHistory>> getAllByDay(@RequestParam("year") int year,@RequestParam("month") int month,
												   @RequestParam("day") int day)
	{
		return ResultReturn.success(garageHistoryService.findAllByDay(year,month,day));
	}

	/**
	 * @param year  年份
	 * @param month 月份
	 * @return 返回日车库历史
	 */
	@RequestMapping("/garagehistory/getallbymonth")
	public Result<List<GarageHistory>> getAllByMonth(@RequestParam("year") int year,@RequestParam("month") int month)
	{
		return ResultReturn.success(garageHistoryService.findAllByMonth(year,month));
	}

	/**
	 * @param year 年份
	 * @return 返回日车库历史
	 */
	@RequestMapping("/garagehistory/getallbyyear")
	public Result<List<GarageHistory>> getAllByYear(@RequestParam("year") int year)
	{
		return ResultReturn.success(garageHistoryService.findAllByYear(year));
	}

	/**
	 * 注意！！此方法只于GarageController中调用！！
	 *
	 * @param garage  车库对象
	 * @param endTime 出库时间
	 * @return 返回当前车库记录
	 */
	Result garageHistoryInsertLog(Garage garage,Timestamp endTime)
	{
		GarageHistory newGarageHistory=new GarageHistory();

		System.out.println(garage);

		newGarageHistory.setEndtime(endTime);
		newGarageHistory.setBrand(garage.getBrand());
		newGarageHistory.setGarageid(garage.getGarageno());
		newGarageHistory.setStarttime(garage.getStarttime());
		newGarageHistory.setType(garage.getType());
		if(garage.getType()==0)
		{
			newGarageHistory.setPrice(new TimeStampUtil().getHoursFromTwoTimeStamp(garage.getStarttime(),
					endTime)*GarageUtil.getGaragePricePreHour());

			new FinanceController().insert(newGarageHistory);
		}
		return ResultReturn.success(garageHistoryService.save(newGarageHistory));
	}
}
