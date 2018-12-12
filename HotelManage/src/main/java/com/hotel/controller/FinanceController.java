package com.hotel.controller;

import com.hotel.exception.ExceptionType;
import com.hotel.exception.HotelException;
import com.hotel.model.Finance;
import com.hotel.model.GarageHistory;
import com.hotel.model.OrderHistory;
import com.hotel.model.Result;
import com.hotel.service.FinanceService;
import com.hotel.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FinanceController
{
	@Autowired
	FinanceService financeService;

	/**
	 * @return 返回全部财务报表
	 */
	@RequestMapping("/finance/getall")
	public Result<Finance> getAll()
	{
		return ResultReturn.success(financeService.findAll());
	}

	/**
	 * @param year  欲查询日财务报表的年份
	 * @param month 欲查询日财务报表的月份
	 * @param day   欲查询日财务报表的日数
	 * @return 返回日财务报表
	 */
	@RequestMapping("/finance/getbyday")
	public Result<Finance> getByDay(@RequestParam("year") int year,@RequestParam("month") int month,
									@RequestParam("day") int day)
	{
		return ResultReturn.success(financeService.findByDay(year,month,day));
	}

	/**
	 * @param year  欲查询月财务报表的年份
	 * @param month 欲查询月财务报表的月份
	 * @return 返回月财务报表
	 */
	@RequestMapping("/finance/getbymonth")
	public Result<Finance> getByDay(@RequestParam("year") int year,@RequestParam("month") int month)
	{
		return ResultReturn.success(financeService.findByMonth(year,month));
	}

	/**
	 * @param year 欲查询年财务报表的年份
	 * @return 返回年财务报表
	 */
	@RequestMapping("/finance/getbyyear")
	public Result<Finance> getByDay(@RequestParam("year") int year)
	{
		return ResultReturn.success(financeService.findByYear(year));
	}

	/**
	 * @param object 收入对象
	 * @throws HotelException 抛出异常收入的错误
	 */
	public void insert(Object object) throws HotelException
	{
		if(object instanceof OrderHistory)
		{
			Finance finance=new Finance();

			finance.setMoney(((OrderHistory)object).getPrice());
			finance.setTime(((OrderHistory)object).getEndtime());
			finance.setType("订单");

			financeService.save(finance);
		}
		else if(object instanceof GarageHistory)
		{
			Finance finance=new Finance();

			finance.setMoney(((GarageHistory)object).getPrice());
			finance.setTime(((GarageHistory)object).getEndtime());
			finance.setType("车库");

			financeService.save(finance);
		}
		else
		{
			throw new HotelException(ExceptionType.FINANCE_INSERT_TYPE_ERROR.getCode(),
					ExceptionType.FINANCE_INSERT_TYPE_ERROR.getMsg());
		}
	}
}
