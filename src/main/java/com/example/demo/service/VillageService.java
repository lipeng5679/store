package com.example.demo.service;

import com.example.demo.domain.City;
import com.example.demo.domain.Village;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface VillageService {

    Village findById(Serializable cid);

    void insert(Village village);

    List<Village> getBycid(Serializable cid);

}
