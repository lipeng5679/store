package com.example.demo.service;

import com.example.demo.domain.Order;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface OrderService {

    Order findCommondityById(Serializable oid);

    void saveRelation(Map map);

    void insertOrder(Order order);

    List<Order> findOrderByuid(Serializable uid);

    Order findCommoditynum(Serializable oid);

    void update(Order order);

    List<Order> findAllorderByModuleId(Serializable moduleId);

    List<Order> findAllorderByModuleIdAndUserId(Map map);

    Order findOrderByorderNo(Serializable orderNo);
}
