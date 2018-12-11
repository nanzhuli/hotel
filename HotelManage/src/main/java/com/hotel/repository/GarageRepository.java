package com.hotel.repository;

import com.hotel.model.Garage;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface GarageRepository extends JpaRepository<Garage,Integer>
{

}
