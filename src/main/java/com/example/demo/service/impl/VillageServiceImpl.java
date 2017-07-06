package com.example.demo.service.impl;

import com.example.demo.domain.City;
import com.example.demo.domain.Village;
import com.example.demo.mapper.CityMapper;
import com.example.demo.mapper.VillageMapper;
import com.example.demo.service.CityService;
import com.example.demo.service.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
@Service
public class VillageServiceImpl implements VillageService {

    @Autowired
    private VillageMapper villageMapper;

    @Override
    public Village findById(Serializable cid) {
        return villageMapper.findById(cid);
    }

    @Override
    public void insert(Village village) {
        villageMapper.insert(village);
    }

    @Override
    public List<Village> getBycid(Serializable cid) {
        return villageMapper.getBycid(cid);
    }


}
