package com.example.demo.service;

import com.example.demo.domain.Commodity;
import com.example.demo.domain.CommodityClass;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface CommodityService {

    List<Commodity> findAll();

    void insert(Commodity commodity);
}
