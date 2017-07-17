package com.example.demo.service.impl;

import com.example.demo.domain.Order;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/16.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order findCommondityById(Serializable oid) {
        return orderMapper.findCommondityById(oid);
    }

    @Override
    public void saveRelation(Map map) {
        orderMapper.saveRelation(map);
    }

    @Override
    public void insertOrder(Order order) {
        orderMapper.insertOrder(order);
    }

    @Override
    public List<Order> findOrderByuid(Serializable uid) {
        return orderMapper.findOrderByuid(uid);
    }

    @Override
    public List<Order> findCommoditynum(Serializable oid) {
        return orderMapper.findCommoditynum(oid);
    }

    @Override
    public void update(Order order) {
        orderMapper.update(order);
    }
}
