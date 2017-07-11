package com.example.demo.service;

import com.example.demo.domain.City;
import com.example.demo.domain.OrderList;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface OrderListService {

    void insert(OrderList orderList);

    List<OrderList> getCommodityNum(Serializable oid);

}
