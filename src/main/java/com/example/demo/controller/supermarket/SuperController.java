package com.example.demo.controller.supermarket;

import com.example.demo.domain.*;
import com.example.demo.service.*;
import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
    @Autowired
    private CommentsService commentsService;

    //跳转超市首页
    @GetMapping("/index")
    private ModelAndView toIndex(){
        ModelAndView modelAndView = new ModelAndView();
        List<CommodityClass> commodityClassList = commodityClassService.findAllcommodityAndclass();
        System.out.println(commodityClassList);

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

    //添加评价
    @PostMapping("/eval")
    public ModelAndView eval(@RequestBody Comments comments,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User)request.getSession().getAttribute("user");
        comments.setDateTime(new Date());
        if(user != null){
            System.out.println(user);
            comments.setUser(user);
        }
        commentsService.insert(comments);

        return modelAndView;
    }

    //处理购物车
    @PostMapping("/car/{id}")
    @ResponseBody
    public String Car(@PathVariable Long id,String state,HttpServletRequest request){
        Commodity commodity = commodityService.getById(id);
        HttpSession session = request.getSession();
        Map<Commodity,Integer> car = (Map<Commodity, Integer>) session.getAttribute("car");
        if(car == null){
            car = new ConcurrentHashMap<Commodity,Integer>();
        }
        //便利map中的商品对象
        Iterator<Commodity> it = car.keySet().iterator();
        boolean f = true;

        //如果是添加商品
        if("add".equals(state)){
            //如果购物车存在相同id商品则在数量上加1
            while (it.hasNext()) {
                Commodity c = (Commodity)it.next();
                if (c.getCommodityId().equals(commodity.getCommodityId())) {
                    car.put(c, car.get(c)+1);
                    f = false;
                }
            }
            if(f){
                car.put(commodity,1);
            }

        }
        //如果是减少商品
        else if("minus".equals(state)){
            while (it.hasNext()){
                Commodity c = (Commodity)it.next();
                if(c.getCommodityId().equals(commodity.getCommodityId())){
                    Integer num = car.get(c);
                    if(num > 1){
                        car.put(c,car.get(c)-1);
                    }else{
                        car.remove(c);
                    }
                }
            }
        }
        session.setAttribute("car", car);

        return "success";
    }

}
