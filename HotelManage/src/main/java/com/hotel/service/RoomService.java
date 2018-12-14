package com.hotel.service;

import com.hotel.exception.ExceptionType;
import com.hotel.exception.HotelException;
import com.hotel.model.Room;
import com.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService
{
	@Autowired
	RoomRepository roomrepository;

	public Iterable<Room> getAll()
	{
		return roomrepository.findAll();
	}

	public Room save(Room r)
	{
		return roomrepository.save(r);
	}

	public Room findByRoom(int roomno) throws HotelException
	{
		Room room=roomrepository.findById(roomno).orElse(null);

		if(room!=null)
		{
			return room;
		}
		else
		{
			throw new HotelException(ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getCode(),
					ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getMsg());
		}
	}

	public void delete(Room r)
	{
		roomrepository.delete(r);
	}

	public List<Room> getEmpty(List<Integer> l)
	{
		return roomrepository.findByRoomnoNotIn(l);
	}

}
