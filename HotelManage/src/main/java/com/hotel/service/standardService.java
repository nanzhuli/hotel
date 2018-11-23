package com.hotel.service;

import com.hotel.model.standard;
import com.hotel.repository.standardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class standardService {

    @Autowired
    private standardRepository standardrespository;

    public List<standard> getAll() {
        return (List<standard>) standardrespository.findAll();
    }

}
