package com.example.demo.mapper;

import com.example.demo.domain.Commodity;
import com.example.demo.domain.CommodityClass;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
@Component
@Mapper
public interface CommodityMapper {

    List<Commodity> findAll();

    void insert(Commodity commodity);


}
