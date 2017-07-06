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
    public City findById(Serializable cid) {
        return cityMapper.findById(cid);
    }

    @Override
    public List<City> findAll() {
        return cityMapper.findAll();
    }

    @Override
    public void insert(City city) {
        cityMapper.insert(city);
    }

    @Override
    public City getByCityname(String cityname) {
        return cityMapper.getByCityname(cityname);
    }

    @Override
    public List<City> getByCityping(String ping) {
        return cityMapper.getByCityping(ping);
    }


}
