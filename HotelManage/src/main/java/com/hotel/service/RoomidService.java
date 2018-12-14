package com.hotel.service;

import com.hotel.exception.ExceptionType;
import com.hotel.exception.HotelException;
import com.hotel.model.Roomid;
import com.hotel.repository.RoomidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomidService
{
	@Autowired
	RoomidRepository roomidrepository;

	public List<Roomid> findAll(int roomno)
	{
		Roomid or=new Roomid();
		or.setRoomno(roomno);
		ExampleMatcher exampleMatcher=ExampleMatcher.matching().withMatcher("roomno",
				ExampleMatcher.GenericPropertyMatchers.contains()).withIgnorePaths("rino","name","id");
		Example<Roomid> ex=Example.of(or,exampleMatcher);
		return roomidrepository.findAll(ex);
	}

	public Roomid findByRino(int rino) throws HotelException
	{
		Roomid roomid=roomidrepository.findById(rino).orElse(null);

		if(roomid!=null)
		{
			return roomid;
		}
		else
		{
			throw new HotelException(ExceptionType.ROOMID_FIND_BY_ROOMIDNO_ERROR.getCode(),
					ExceptionType.ROOMID_FIND_BY_ROOMIDNO_ERROR.getMsg());
		}
	}

	public List<Roomid> findAllWithoutparam()
	{
		return roomidrepository.findAll();
	}

	public Roomid save(Roomid e)
	{
		return roomidrepository.save(e);
	}

	public List<Roomid> saveAll(List<Roomid> ri)
	{
		return roomidrepository.saveAll(ri);
	}

}
