package com.example.demo.mapper;

import com.example.demo.domain.City;
import com.example.demo.domain.Img;
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
public interface ImgMapper {

    List<Img> findAll();
    void insert(Img img);

    Img get(Serializable id);

}
