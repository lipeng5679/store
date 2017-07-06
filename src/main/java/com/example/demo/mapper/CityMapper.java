package com.example.demo.mapper;

import com.example.demo.domain.City;
import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
@Component
@Mapper
public interface CityMapper {

    City findById(Serializable cid);

    List<City> findAll();

    void insert(City city);

    City getByCityname(String cityname);

    List<City> getByCityping(String ping);

}
