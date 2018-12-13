package com.hotel.repository;

import com.hotel.model.OrderRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface orderroomRepository extends JpaRepository<OrderRoom, Integer> {
}
