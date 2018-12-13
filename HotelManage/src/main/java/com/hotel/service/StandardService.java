package com.hotel.service;

import com.hotel.model.Standard;
import com.hotel.repository.StandardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandardService
{

    @Autowired
    private StandardRepository standardrespository;

    public List<Standard> getAll() {
        return (List<Standard>) standardrespository.findAll();
    }

}
