package com.example.demo.controller;

import com.example.demo.domain.Commodity;
import com.example.demo.domain.CommodityClass;
import com.example.demo.domain.Order;
import com.example.demo.domain.User;
import com.example.demo.service.CommodityClassService;
import com.example.demo.service.CommodityService;
import com.example.demo.service.ModuleService;
import com.example.demo.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/10.
 */
@Controller
@Slf4j
public class SysController {

    @Autowired
    private CommodityClassService commodityClassService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private OrderService orderService;

    //跳转后台管理添加商品页面
    @GetMapping("/admin/addcommodity")
    public String toaddCommodity(ModelMap modelMap){
        List<CommodityClass> all = commodityClassService.findAll();
        modelMap.put("all",all);
        return "admin/supermarket/addcommodity";
    }

    //添加商品
    @PostMapping("/admin/addcommodity")
    public ModelAndView addCommodity(String commodityClassName, Commodity commodity){
        ModelAndView modelAndView = new ModelAndView();

        CommodityClass commodityClass = commodityClassService.getCommodityClassByName(commodityClassName);
        if(commodityClass == null){
            CommodityClass commodityClass1 = new CommodityClass();
            commodityClass1.setCommodityClassName(commodityClassName);
            commodityClassService.insert(commodityClass1);
            commodity.setC_id(commodityClass1.getCommodityClassId());
            commodityService.insert(commodity);

        }else {
            commodity.setC_id(commodityClass.getCommodityClassId());
            commodityService.insert(commodity);
        }


        modelAndView.setViewName("admin/index");
        return modelAndView;
    }

    //跳转后台管理修改全部商品
    @GetMapping("/admin/editcommodity")
    public String toeditCommodity(
            @RequestParam(required = true, defaultValue = "1") Integer pageNum,
            @RequestParam(required = true, defaultValue = "5") Integer pageSize,
            ModelMap modelMap){
        PageHelper.startPage(pageNum, pageSize);// 默认从第一页开始，每页五条
        List<Commodity> commodityList = commodityService.findcommodityAndclass();
        modelMap.put("commodityList",commodityList);
        PageHelper.startPage(pageNum, pageSize);// 默认从第一页开始，每页五条
        PageInfo<Commodity> pageUser = new PageInfo<Commodity>(commodityList);// 将users对象绑定到pageInfo
        modelMap.put("pageUser",pageUser);

        return "admin/supermarket/editcommodity";
    }

    //修改商品状态
    @PostMapping("/edit/{commodityId}")
    @ResponseBody
    public String editState(@PathVariable Long commodityId,String state){
        Commodity commodity = commodityService.getById(commodityId);
        switch (state){
            case "0":
                commodity.setState(0);
                break;
            case "1":
                commodity.setState(1);
                break;
            case "2":
                commodity.setState(2);
                break;
        }
        commodityService.update(commodity);

        return "success";
    }

    //跳转到修改单个商品
    @GetMapping("/admin/{commodityId}")
    public String edit(@PathVariable Long commodityId, ModelMap modelMap){
        List<CommodityClass> all = commodityClassService.findAll();
        modelMap.put("all",all);
        Commodity commodity = commodityService.getById(commodityId);
        CommodityClass commodityClass = commodityClassService.getCommodityClassById(commodity.getC_id());
        modelMap.put("commodity",commodity);
        modelMap.put("commodityClass",commodityClass);

        return "admin/supermarket/edit";
    }

    //修改商品信息
    @PostMapping("/admin/{commodityId}")
    public String editsucess(@PathVariable Long commodityId,Commodity commodity,String commodityClassName){
        CommodityClass commodityClass = commodityClassService.getCommodityClassByName(commodityClassName);
        commodity.setC_id(commodityClass.getCommodityClassId());
        commodityService.update(commodity);

        return "admin/index";
    }

    //跳转到后台超市订单修改页面
    @GetMapping("/admin/editorder")
    public String toeditorder(ModelMap modelMap){
        List<Order> orderList = orderService.findAllorderByModuleId(1);
        modelMap.put("orderList",orderList);
        System.out.println(orderList);

        return "admin/supermarket/editorder";
    }

    //修改订单状态
    @GetMapping("/admin/updateorder/{orderId}")
    public String updateorder(@PathVariable Long orderId){
        Order order = orderService.findCommondityById(orderId);
        int iscon = order.getIscon();       //0未评价  1已评价 2已退款
        int isPayoff = order.getIsPayoff(); //0未支付  1已支付  2配送中  3已送达
        if(isPayoff == 1){
            order.setIsPayoff(2);
        }else if(isPayoff == 2){
            order.setIsPayoff(3);
        }
        orderService.update(order);

        return "redirect:/admin/editorder";
    }

    //查看订单
    @GetMapping("/admin/show/{orderNo}")
    public String showorder(@PathVariable String orderNo,ModelMap modelMap){
        Order order = orderService.findOrderByorderNo(orderNo);
        modelMap.put("order",order);
        System.out.println(order);

        return "admin/supermarket/showorder";
    }

}
