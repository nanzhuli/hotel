package com.hotel.repository;

import com.hotel.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
@org.springframework.stereotype.Repository
public interface orderRepository extends JpaRepository<Order, Integer> {

}
