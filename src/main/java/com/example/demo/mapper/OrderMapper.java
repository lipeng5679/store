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

    /**
     * 查询订单信息
     * @param oid
     * @return
     */
    Order findCommondityById(Serializable oid);

    void saveRelation(Map map);

    void insertOrder(Order order);

    List<Order> findOrderByuid(Serializable uid);

    List<Order> findCommoditynum(Serializable oid);

    void update(Order order);

    List<Order> findAllorderByModuleId(Serializable moduleId);

    List<Order> findAllorderByModuleIdAndUserId(Map map);

    Order findOrderByorderNo(Serializable orderNo);

}
