package com.example.demo.controller;

import com.example.demo.domain.Commodity;
import com.example.demo.domain.CommodityClass;
import com.example.demo.service.CommodityClassService;
import com.example.demo.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
@Controller
public class TestController {

    @Autowired
    private CommodityClassService commodityClassService;
    @Autowired
    private CommodityService commodityService;

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


        return "test";
    }

}
