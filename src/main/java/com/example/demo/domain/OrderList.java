package com.example.demo.domain;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/9.
 */
//订单列表
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderList implements Serializable{
    private Long orderListId;
    private Long order_Id;          //订单id
    private Long commodity_Id;      //商品id
    private int commoditynum;       //商品数量
}
