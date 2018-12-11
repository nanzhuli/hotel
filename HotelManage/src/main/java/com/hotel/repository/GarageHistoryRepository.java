package com.hotel.repository;

import com.hotel.model.GarageHistory;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface GarageHistoryRepository extends JpaRepository<GarageHistory,Integer>
{
}
