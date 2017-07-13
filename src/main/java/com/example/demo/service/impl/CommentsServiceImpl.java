package com.example.demo.service.impl;

import com.example.demo.domain.City;
import com.example.demo.domain.Comments;
import com.example.demo.mapper.CityMapper;
import com.example.demo.mapper.CommentsMapper;
import com.example.demo.service.CityService;
import com.example.demo.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
@Service

public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsMapper commentsMapper;

    @Override
    public List<Comments> findAll() {
        return commentsMapper.findAll();
    }

    @Override
    public void insert(Comments comments) {
        commentsMapper.insert(comments);
    }
}
