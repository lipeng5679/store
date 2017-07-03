package com.example.demo.service;

import com.example.demo.domain.City;
import com.example.demo.domain.Img;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface ImgService {

    List<Img> findAll();

    void insert(Img img);

    Img get(Serializable id);
}
