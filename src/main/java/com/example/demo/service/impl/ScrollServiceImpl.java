package com.example.demo.service.impl;

import com.example.demo.domain.Img;
import com.example.demo.domain.Scroll;
import com.example.demo.mapper.ImgMapper;
import com.example.demo.mapper.ScrollMapper;
import com.example.demo.service.ImgService;
import com.example.demo.service.ScrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
@Service
public class ScrollServiceImpl implements ScrollService {

    @Autowired
    private ScrollMapper scrollMapper;


    @Override
    public List<Scroll> findAll() {
        return scrollMapper.findAll();
    }

    @Override
    public void insert(Scroll scroll) {
        scrollMapper.insert(scroll);
    }

    @Override
    public void deleteAll() {
        scrollMapper.deleteAll();
    }
}
