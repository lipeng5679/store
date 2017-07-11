package com.example.demo.service;

import com.example.demo.domain.Commodity;
import com.example.demo.domain.CommodityClass;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface CommodityService {

    List<Commodity> findAll();

    void insert(Commodity commodity);

    Commodity getById(Serializable commodityId);

    List<Commodity> getByclassId(Map map);

    void update(Commodity commodity);

    List<Commodity> findcommodityAndclass();
}
