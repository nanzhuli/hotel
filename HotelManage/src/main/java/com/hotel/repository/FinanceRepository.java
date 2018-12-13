package com.hotel.repository;

import com.hotel.model.Finance;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface FinanceRepository extends JpaRepository<Finance,Integer>
{
}
