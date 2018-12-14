package com.hotel.controller;

import com.hotel.model.Garage;
import com.hotel.model.GarageHistory;
import com.hotel.service.GarageHistoryService;
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
	
	@Autowired
	GarageHistoryService garageHistoryService;

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
	public Result<Garage> garageFindByBrand(@PathVariable("brand") String brand)
	{
		return ResultReturn.success(garageService.findByBrand(brand));
	}

	/**
	 * @param garageNo 车位
	 * @return 返回查询到的车库
	 */
	@RequestMapping("/garage/getonebyid/{garageno}")
	public Result<Garage> garageFindById(@PathVariable("garageno") int garageNo)
	{
		return ResultReturn.success(garageService.findById(garageNo));
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
	public Result<Garage> garageUpdate(@RequestParam("garageno") int garageNo,@RequestParam("type") int type,
									   @RequestParam("starttime") Timestamp startTime,
									   @RequestParam("endtime") Timestamp endTime,@RequestParam("brand") String brand)
	{
		Garage garage=garageService.findById(garageNo);
		garage.setType(type);
		garage.setStarttime(startTime);
		garage.setEndtime(endTime);
		garage.setBrand(brand);

		return ResultReturn.success(garageService.save(garage));
	}

	/**
	 * @param garageNo 车库(位)编号
	 * @param type     类型
	 * @param brand    车牌
	 * @return 返回更新车库结果
	 */
	@RequestMapping("/garage/drivein")
	public Result<Garage> garageDriveIn(@RequestParam("garageno") int garageNo,@RequestParam("type") int type,
										@RequestParam("brand") String brand)
	{
		Garage garage=garageService.findById(garageNo);
		garage.setType(type);
		garage.setStarttime(new Timestamp(System.currentTimeMillis()));
		garage.setEndtime(new Timestamp(0));
		garage.setBrand(brand);

		return ResultReturn.success(garageService.save(garage));
	}

	/**
	 * @param garageNo 车库(位)编号
	 * @return 返回出库结果
	 */
	@RequestMapping("/garage/driveout")
	public Result garageDriveOut(@RequestParam("garageno") int garageNo)
	{
		Garage garage=garageService.findById(garageNo);

		System.out.println(garage.hashCode()+": "+garage.toString());

		Timestamp endTime=new Timestamp(System.currentTimeMillis());

		Garage garageTemp=new Garage();
		garageTemp.setType(garage.getType());
		garageTemp.setBrand(garage.getBrand());
		garageTemp.setStarttime(garage.getStarttime());
		garageTemp.setEndtime(garage.getEndtime());
		garageTemp.setGarageno(garage.getGarageno());

		garageUpdate(garage.getGarageno(),0,null,null,null);

		return new GarageHistoryController(garageHistoryService).garageHistoryInsertLog(garageTemp,endTime);
	}

	/**
	 * @param number 想要增加车位的数量
	 * @return 返回车库扩增结果(增加车位的具体信息)
	 */
	@RequestMapping("/garage/insertnullgarage/{number}")
	public Result<List<Garage>> garageInsertNullGarage(@PathVariable("number") int number)
	{
		List<Garage> garageList=new ArrayList<>();
		int temp=number;
		do
		{
			Garage newGarage=new Garage();

			if(temp==number)
			{
				newGarage.setGarageno(1);
			}
			newGarage.setBrand(null);
			newGarage.setStarttime(null);
			newGarage.setEndtime(null);
			newGarage.setType(0);
			garageList.add(garageService.save(newGarage));
			garageService.save(newGarage);
		}while (--number>0);

		return ResultReturn.success(garageList);
	}
}
