package com.example.demo.mapper;

import com.example.demo.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/16.
 */
@Component
@Mapper
public interface OrderMapper {

    Order findCommondityById(Serializable oid);

    void saveRelation(Map map);

    void insertOrder(Order order);

    List<Order> findOrderByuid(Serializable uid);

}
