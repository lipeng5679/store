package com.example.demo.controller;

import com.example.demo.domain.Commodity;
import com.example.demo.domain.Order;
import com.example.demo.service.CommodityClassService;
import com.example.demo.service.CommodityService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

/**
 * Created by Administrator on 2017/7/6.
 */
@Controller
public class TestController {

    @Autowired
    private CommodityClassService commodityClassService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/test")
    private String toTest(){

        /*CommodityClass commodityClass = new CommodityClass(1l,"零食");
        commodityClassService.insert(commodityClass);
        List<CommodityClass> all = commodityClassService.findAll();
        System.out.println(all);*/


        /*Commodity commodity = new Commodity("泡面","吃的",4,100,"baidu",1);
        commodityService.insert(commodity);
        List<Commodity> all = commodityService.findAll();
        System.out.println(all);*/

        /*List<Order> orders = userService.findOrdersById(1);
        System.out.println(orders);*/

        /*Map map = new HashMap();
        map.put("id",1);
        map.put("state",1);
        List<Commodity> commodities = commodityService.getByclassId(map);
        System.out.println(commodities);*/

        /*Order order = orderService.findCommondityById(2);
        List<Commodity> commodities = order.getCommodities();
        for (Commodity c:commodities
             ) {
            System.out.println(c);
        }*/
        /*Order order1 = orderService.findCommondityById(1);
        for (Commodity c:order1.getCommodities()
             ) {
            System.out.println(c);
        }*/
        /*Order order = new Order();
        order.setOrderNo("004");
        orderService.insertOrder(order);
        List list = new ArrayList();
        for(int i=0;i<=3;i++){
            Commodity commodity = new Commodity();
            commodity.setState(i);
            commodityService.insert(commodity);
            Map map = new HashMap();
            map.put("orderId",order.getOrderId());
            map.put("commodityId",commodity.getCommodityId());
            orderService.saveRelation(map);
            list.add(commodity);
        }*/

        return "test";
    }

    @GetMapping("/elm")
    public String toelm(){
        return "elem";
    }

}
