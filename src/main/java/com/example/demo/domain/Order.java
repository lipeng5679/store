package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */

//订单
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable{

    private Long orderId;
    private String orderNo;     //订单号
    private Date submitTime;    //订单提交时间
    private Date conTime;       //预定时间
    private Double totalPrice;  //总金额
    private int isPayoff;       //是否付款  0未支付  1已支付  2配送中  3已送达
    private int iscon;          //是否评价  0未评价  1已评价 2已退款
    private int sum;            //商品总数

    private User user;          //订单用户信息
    private List<Commodity> commodities;    //多个商品信息
    private List<OrderList> orderLists;    //订单与商品之间的关系
    private Module module;      //模块信息
}
