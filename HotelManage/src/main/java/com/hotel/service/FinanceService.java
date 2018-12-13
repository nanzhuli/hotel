package com.hotel.service;

import com.hotel.exception.ExceptionType;
import com.hotel.exception.HotelException;
import com.hotel.model.Finance;
import com.hotel.repository.FinanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FinanceService
{
	@Autowired
	FinanceRepository financeRepository;

	public List<Finance> findAll()
	{
		return financeRepository.findAll();
	}

	public List<Finance> findByDay(int year,int month,int day) throws HotelException
	{
		List<Finance> financeList=new ArrayList<>();

		for (Finance finance : financeRepository.findAll())
		{
			if(finance.getTime().getYear()==year && finance.getTime().getMonth()==month && finance.getTime().getDay()==day)
			{
				financeList.add(finance);
			}
		}

		if(financeList.size()>0)
		{
			return financeList;
		}
		else
		{
			throw new HotelException(ExceptionType.FINANCE_FIND_BY_DAY_ERROR.getCode(),
					ExceptionType.FINANCE_FIND_BY_DAY_ERROR.getMsg());
		}
	}

	public List<Finance> findByMonth(int year,int month) throws HotelException
	{
		List<Finance> financeList=new ArrayList<>();

		for (Finance finance : financeRepository.findAll())
		{
			if(finance.getTime().getYear()==year && finance.getTime().getMonth()==month)
			{
				financeList.add(finance);
			}
		}

		if(financeList.size()>0)
		{
			return financeList;
		}
		else
		{
			throw new HotelException(ExceptionType.FINANCE_FIND_BY_MONTH_ERROR.getCode(),
					ExceptionType.FINANCE_FIND_BY_MONTH_ERROR.getMsg());
		}
	}

	public List<Finance> findByYear(int year) throws HotelException
	{
		List<Finance> financeList=new ArrayList<>();

		for (Finance finance : financeRepository.findAll())
		{
			if(finance.getTime().getYear()==year)
			{
				financeList.add(finance);
			}
		}

		if(financeList.size()>0)
		{
			return financeList;
		}
		else
		{
			throw new HotelException(ExceptionType.FINANCE_FIND_BY_YEAR_ERROR.getCode(),
					ExceptionType.FINANCE_FIND_BY_YEAR_ERROR.getMsg());
		}
	}

	public Finance save(Finance finance)
	{
		return financeRepository.save(finance);
	}
}
