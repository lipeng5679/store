package com.example.demo.controller.supermarket;

import com.example.demo.domain.*;
import com.example.demo.service.CommodityClassService;
import com.example.demo.service.CommodityService;
import com.example.demo.service.OrderListService;
import com.example.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderListService orderListService;

    //跳转超市首页
    @GetMapping("/index")
    private ModelAndView toIndex(){
        ModelAndView modelAndView = new ModelAndView();
        List<CommodityClass> commodityClassList = commodityClassService.findAll();
        List<Commodity> commodityList = commodityService.findAll();

        modelAndView.addObject("commodityClassList",commodityClassList);
        modelAndView.addObject("commodityList",commodityList);
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

    //跳转订单页面
    @GetMapping("/orderlist")
    public ModelAndView toOrder(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) request.getSession().getAttribute("user");
        List<Order> orderList = orderService.findOrderByuid(user.getId());
        for (Order o:orderList
             ) {
            List<Commodity> commodityList = o.getCommodities();
        }


        modelAndView.setViewName("supermarket/orderlist");
        return modelAndView;
    }

    //跳转结算订单页面
    @GetMapping("/order")
    public ModelAndView Order(){
        ModelAndView modelAndView = new ModelAndView();


        modelAndView.setViewName("supermarket/order");
        return modelAndView;
    }

    //跳转评价页面
    @GetMapping("/eval")
    public ModelAndView toEval(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("supermarket/eval");
        return modelAndView;
    }

}
