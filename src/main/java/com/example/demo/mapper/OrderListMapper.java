package com.example.demo.mapper;

import com.example.demo.domain.City;
import com.example.demo.domain.OrderList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
@Component
@Mapper
public interface OrderListMapper {

    void insert(OrderList orderList);

    List<OrderList> getCommodityNum(Serializable oid);

}
