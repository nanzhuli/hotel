package com.hotel.service;

import com.hotel.exception.ExceptionType;
import com.hotel.exception.HotelException;
import com.hotel.model.GarageHistory;
import com.hotel.repository.GarageHistoryRepository;
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

	public List<GarageHistory> findAllById(int garageNo)
	{
		List<GarageHistory> garageHistoryList=new ArrayList<GarageHistory>();
		for (GarageHistory garageHistory : garageHistoryRepository.findAll())
		{
			if(garageHistory.getGarageNo()==garageNo)
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
}
