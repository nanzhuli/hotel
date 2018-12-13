package com.hotel.service;

import com.hotel.exception.ExceptionType;
import com.hotel.exception.HotelException;
import com.hotel.model.Garage;
import com.hotel.model.GarageHistory;
import com.hotel.repository.GarageHistoryRepository;
import com.hotel.util.TimeStampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GarageHistoryService
{
	@Autowired
	GarageHistoryRepository garageHistoryRepository;

	public List<GarageHistory> findAll()
	{
		return garageHistoryRepository.findAll();
	}

	public List<GarageHistory> findAllById(int garageId)
	{
		List<GarageHistory> garageHistoryList=new ArrayList<GarageHistory>();
		for (GarageHistory garageHistory : garageHistoryRepository.findAll())
		{
			if(garageHistory.getGarageid()==garageId)
			{
				garageHistoryList.add(garageHistory);
			}
		}

		if(garageHistoryList.size()>0)
		{
			return garageHistoryList;
		}
		else
		{
			throw new HotelException(ExceptionType.GARAGE_HISTORY_FIND_BY_ID_ERROR.getCode(),
					ExceptionType.GARAGE_HISTORY_FIND_BY_ID_ERROR.getMsg());
		}
	}

	public List<GarageHistory> findAllByBrand(String brand) throws HotelException
	{
		List<GarageHistory> garageHistoryList=new ArrayList<GarageHistory>();
		for (GarageHistory garageHistory : garageHistoryRepository.findAll())
		{
			if(garageHistory.getBrand().equals(brand))
			{
				garageHistoryList.add(garageHistory);
			}
		}

		if(garageHistoryList.size()>0)
		{
			return garageHistoryList;
		}
		else
		{
			throw new HotelException(ExceptionType.GARAGE_HISTORY_FIND_BY_BRAND_ERROR.getCode(),
					ExceptionType.GARAGE_HISTORY_FIND_BY_BRAND_ERROR.getMsg());
		}
	}

	public List<GarageHistory> findAllByDay(int year,int month,int day) throws HotelException
	{
		List<GarageHistory> garageHistoryList=new ArrayList<>();

		for (GarageHistory garageHistory : garageHistoryRepository.findAll())
		{
			String[] strings=new TimeStampUtil().getStringArray(garageHistory.getEndtime());
			if(strings[0].equals(Integer.toString(year)) && strings[1].equals(
					Integer.toString(month)) && strings[2].equals(Integer.toString(day)))
			{
				garageHistoryList.add(garageHistory);
			}
		}

		if(garageHistoryList.size()>0)
		{
			return garageHistoryList;
		}
		else
		{
			throw new HotelException(ExceptionType.GARAGE_HISTORY_FIND_BY_DAY_ERROR.getCode(),
					ExceptionType.GARAGE_HISTORY_FIND_BY_DAY_ERROR.getMsg());
		}
	}

	public List<GarageHistory> findAllByMonth(int year,int month) throws HotelException
	{
		List<GarageHistory> garageHistoryList=new ArrayList<>();

		for (GarageHistory garageHistory : garageHistoryRepository.findAll())
		{
			String[] strings=new TimeStampUtil().getStringArray(garageHistory.getEndtime());
			if(strings[0].equals(Integer.toString(year)) && strings[1].equals(Integer.toString(month)))
			{
				garageHistoryList.add(garageHistory);
			}
		}

		if(garageHistoryList.size()>0)
		{
			return garageHistoryList;
		}
		else
		{
			throw new HotelException(ExceptionType.GARAGE_HISTORY_FIND_BY_MONTH_ERROR.getCode(),
					ExceptionType.GARAGE_HISTORY_FIND_BY_MONTH_ERROR.getMsg());
		}
	}

	public List<GarageHistory> findAllByYear(int year) throws HotelException
	{
		List<GarageHistory> garageHistoryList=new ArrayList<>();

		for (GarageHistory garageHistory : garageHistoryRepository.findAll())
		{
			String[] strings=new TimeStampUtil().getStringArray(garageHistory.getEndtime());
			if(strings[0].equals(Integer.toString(year)))
			{
				garageHistoryList.add(garageHistory);
			}
		}

		if(garageHistoryList.size()>0)
		{
			return garageHistoryList;
		}
		else
		{
			throw new HotelException(ExceptionType.GARAGE_HISTORY_FIND_BY_YEAR_ERROR.getCode(),
					ExceptionType.GARAGE_HISTORY_FIND_BY_YEAR_ERROR.getMsg());
		}
	}

	public GarageHistory save(GarageHistory garageHistory)
	{
		return garageHistoryRepository.save(garageHistory);
	}
}
