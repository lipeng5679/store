package com.example.demo.service.impl;

import com.example.demo.domain.City;
import com.example.demo.domain.Img;
import com.example.demo.mapper.CityMapper;
import com.example.demo.mapper.ImgMapper;
import com.example.demo.service.CityService;
import com.example.demo.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
@Service
public class ImgServiceImpl implements ImgService {

    @Autowired
    private ImgMapper imgMapper;


    @Override
    public List<Img> findAll() {
        return imgMapper.findAll();
    }

    @Override
    public void insert(Img img) {
        imgMapper.insert(img);
    }

    @Override
    public Img get(Serializable id) {
        return imgMapper.get(id);
    }


}
