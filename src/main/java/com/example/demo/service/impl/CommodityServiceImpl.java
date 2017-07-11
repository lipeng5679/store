package com.example.demo.service.impl;

import com.example.demo.domain.Commodity;
import com.example.demo.domain.CommodityClass;
import com.example.demo.mapper.CommodityClassMapper;
import com.example.demo.mapper.CommodityMapper;
import com.example.demo.service.CommodityClassService;
import com.example.demo.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/16.
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public List<Commodity> findAll() {
        return commodityMapper.findAll();
    }

    @Override
    public void insert(Commodity commodity) {
        commodityMapper.insert(commodity);
    }

    @Override
    public Commodity getById(Serializable commodityId) {
        return commodityMapper.getById(commodityId);
    }

    @Override
    public List<Commodity> getByclassId(Map map) {
        return commodityMapper.getByclassId(map);
    }

    @Override
    public void update(Commodity commodity) {
        commodityMapper.update(commodity);
    }

    @Override
    public List<Commodity> findcommodityAndclass() {
        return commodityMapper.findcommodityAndclass();
    }
}
