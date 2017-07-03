package com.example.demo.service;

import com.example.demo.domain.Img;
import com.example.demo.domain.Scroll;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface ScrollService {

    List<Scroll> findAll();
    void insert(Scroll scroll);

    void deleteAll();
}
