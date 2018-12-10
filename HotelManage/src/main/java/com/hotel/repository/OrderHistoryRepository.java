package com.hotel.repository;

import com.hotel.model.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory,Integer>
{

}
