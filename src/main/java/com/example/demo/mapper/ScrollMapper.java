package com.example.demo.mapper;

import com.example.demo.domain.Img;
import com.example.demo.domain.Scroll;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */

//存储所有图片
@Component
@Mapper
public interface ScrollMapper {

    List<Scroll> findAll();
    void insert(Scroll scroll);

    void deleteAll();

}
