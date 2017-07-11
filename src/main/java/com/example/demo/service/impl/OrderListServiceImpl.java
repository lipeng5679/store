package com.example.demo.service.impl;

import com.example.demo.domain.City;
import com.example.demo.domain.OrderList;
import com.example.demo.mapper.CityMapper;
import com.example.demo.mapper.OrderListMapper;
import com.example.demo.service.CityService;
import com.example.demo.service.OrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
@Service
public class OrderListServiceImpl implements OrderListService {

    @Autowired
    private OrderListMapper orderListMapper;

    @Override
    public void insert(OrderList orderList) {
        orderListMapper.insert(orderList);
    }

    @Override
    public List<OrderList> getCommodityNum(Serializable oid) {
        return orderListMapper.getCommodityNum(oid);
    }
}
