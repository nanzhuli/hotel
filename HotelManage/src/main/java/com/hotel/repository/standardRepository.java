package com.hotel.repository;

import com.hotel.model.standard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface standardRepository extends JpaRepository<standard, Integer> {

}
