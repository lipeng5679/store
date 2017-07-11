package com.example.demo.service.impl;

import com.example.demo.domain.City;
import com.example.demo.domain.CommodityClass;
import com.example.demo.mapper.CityMapper;
import com.example.demo.mapper.CommodityClassMapper;
import com.example.demo.service.CityService;
import com.example.demo.service.CommodityClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
@Service
public class CommodityClassServiceImpl implements CommodityClassService{

    @Autowired
    private CommodityClassMapper commodityClassMapper;

    @Override
    public List<CommodityClass> findAll() {
        return commodityClassMapper.findAll();
    }

    @Override
    public void insert(CommodityClass commodityClass) {
        commodityClassMapper.insert(commodityClass);
    }

    @Override
    public CommodityClass getCommodityClassByName(String commodityClassName) {
        return commodityClassMapper.getCommodityClassByName(commodityClassName);
    }

    @Override
    public CommodityClass getCommodityClassById(Serializable commodityClassId) {
        return commodityClassMapper.getCommodityClassById(commodityClassId);
    }
}
