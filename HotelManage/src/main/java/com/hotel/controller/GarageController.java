package com.hotel.controller;

import com.hotel.model.Garage;
import com.hotel.service.GarageService;
import com.hotel.model.Result;
import com.hotel.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GarageController
{
	@Autowired
	GarageService garageService;

	/**
	 * @return 返回车库列表
	 */
	@RequestMapping("/garage/getall")
	public Result<List<Garage>> garageList()
	{
		return ResultReturn.success(garageService.findAll());
	}

	/**
	 * @param brand 车牌
	 * @return 返回查询到的车库
	 */
	@RequestMapping("/garage/getonebybrand/{brand}")
	public Result garageFindByBrand(@PathVariable("brand") String brand)
	{
		Garage garage=garageService.findByBrand(brand);
		return ResultReturn.success(garage);
	}

	/**
	 * @param garageNo  车库(位)编号
	 * @param type      类型
	 * @param startTime 入库时间
	 * @param endTime   出库时间
	 * @param brand     车牌
	 * @return 返回更新车库结果
	 */
	@RequestMapping("/garage/update")
	public Result garageUpdate(@RequestParam("garageno") int garageNo,
							   @RequestParam("type") String type,
							   @RequestParam("starttime") Timestamp startTime,
							   @RequestParam("endtime") Timestamp endTime,
							   @RequestParam("brand") String brand)
	{
		Garage garage=garageService.findById(garageNo);
		garage.setType(type);
		garage.setStarttime(startTime);
		garage.setEndtime(endTime);
		garage.setBrand(brand);

		return ResultReturn.success(garageService.save(garage));
	}

	/**
	 *
	 * @param garageNo  车库(位)编号
	 * @param type      类型
	 * @param startTime 入库时间
	 * @param brand     车牌
	 * @return 返回更新车库结果
	 */
	@RequestMapping("/garage/drivein")
	public Result garageDriveIn(@RequestParam("garageno") int garageNo,
								@RequestParam("type") String type,
								@RequestParam("starttime") Timestamp startTime,
								@RequestParam("brand") String brand)
	{
		Garage garage=garageService.findById(garageNo);
		garage.setType(type);
		garage.setStarttime(startTime);
		garage.setEndtime(new Timestamp(0));
		garage.setBrand(brand);

		return ResultReturn.success(garageService.save(garage));
	}

	/**
	 *
	 * @param garageNo 车库(位)编号
	 * @param endTime 出库时间
	 * @return 返回出库结果
	 */
	@RequestMapping("/garage/driveout")
	public Result garageDriveOut(@RequestParam("garageno") int garageNo,
								 @RequestParam("endtime") Timestamp endTime)
	{
		Garage garage=garageService.findById(garageNo);
		garageUpdate(garage.getGarageno(),null,null,null,null);

		return new GarageHistoryController().garageHistoryInsertLog(garage,endTime);
	}

	/**
	 *
	 * @param number 想要增加车位的数量
	 * @return 返回车库扩增结果(增加车位的具体信息)
	 */
	@RequestMapping("/garage/insertnullgarage/{number}")
	public Result garageInsertNullGarage(@PathVariable("number") int number)
	{
		List<Garage> garageList=new ArrayList<>();

		while(number-->0)
		{
			Garage newGarage=new Garage();
			garageList.add(garageService.save(newGarage));
		}

		return ResultReturn.success(garageList);
	}
}
