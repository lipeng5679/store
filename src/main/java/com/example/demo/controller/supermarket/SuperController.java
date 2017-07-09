package com.example.demo.controller.supermarket;

import com.example.demo.domain.Commodity;
import com.example.demo.domain.CommodityClass;
import com.example.demo.service.CommodityClassService;
import com.example.demo.service.CommodityService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/6.
 */
@Controller
@Slf4j
@RequestMapping("/supermarket")
public class SuperController {

    @Autowired
    private CommodityClassService commodityClassService;
    @Autowired
    private CommodityService commodityService;

    @GetMapping("/index")
    private ModelAndView toIndex(){
        ModelAndView modelAndView = new ModelAndView();
        List<CommodityClass> commodityClassList = commodityClassService.findAll();


        modelAndView.addObject("commodityClassList",commodityClassList);
        modelAndView.setViewName("supermarket/index");
        return modelAndView;
    }

    //根据id获取商品信息
    @PostMapping("/getcommodity")
    @ResponseBody
    public String getcommodity(String id){
        Map map = new HashMap();
        map.put("id",id);
        map.put("state",2); //2表示已经上架的商品
        List<Commodity> commodityList = commodityService.getByclassId(map);
        String json = JSONArray.fromObject(commodityList).toString();

        return json;
    }

}
