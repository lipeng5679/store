package com.example.demo.controller;

import com.example.demo.domain.Commodity;
import com.example.demo.domain.CommodityClass;
import com.example.demo.service.CommodityClassService;
import com.example.demo.service.CommodityService;
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
    public String toeditCommodity(ModelMap modelMap){

        List<Commodity> commodityList = commodityService.findcommodityAndclass();
        modelMap.put("commodityList",commodityList);
        //modelMap.put("map",map);

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

}
