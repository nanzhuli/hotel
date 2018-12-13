package com.hotel.repository;

import com.hotel.model.OrderRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRoomRepository extends JpaRepository<OrderRoom, Integer> {
}
