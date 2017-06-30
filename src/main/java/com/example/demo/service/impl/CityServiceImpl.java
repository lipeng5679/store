package com.example.demo.service.impl;

import com.example.demo.domain.City;
import com.example.demo.domain.User;
import com.example.demo.mapper.CityMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.CityService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public List<City> findAllCity() {
        return cityMapper.findAllCity();
    }
}
