package com.hotel.repository;

import com.hotel.model.employ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@org.springframework.stereotype.Repository
public interface employRepository extends JpaRepository<employ,Integer>{

}
