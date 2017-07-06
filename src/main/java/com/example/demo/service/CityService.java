package com.example.demo.service;

import com.example.demo.domain.City;
import com.example.demo.domain.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface CityService {

    City findById(Serializable cid);

    List<City> findAll();

    void insert(City city);

    City getByCityname(String cityname);

    List<City> getByCityping(String ping);
}
