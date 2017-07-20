package com.example.demo.controller.supermarket;

import com.example.demo.domain.*;
import com.example.demo.service.*;
import com.example.demo.util.MapUtil;
import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    private CommentsService commentsService;
    @Autowired
    private ModuleService moduleService;

    //跳转超市首页
    @GetMapping("/index")
    private ModelAndView toIndex(Long moduleId,HttpServletRequest request){
        request.getSession().setAttribute("module",moduleService.getModuleById(moduleId));

        ModelAndView modelAndView = new ModelAndView();
        List<CommodityClass> commodityClassList = commodityClassService.findAllcommodityAndclass();


        modelAndView.addObject("commodityClassList",commodityClassList);
        modelAndView.setViewName("supermarket/index");

        //准备某模块的评论数据
        HttpSession session = request.getSession();
        Module module = (Module) session.getAttribute("module");

        List<Comments> commentsList = commentsService.findAllBymoduleId(module.getModuleId());
        List<Map> mapList = new ArrayList<>();

        for (Comments c:commentsList
                ) {
            String src = c.getSrc();
            String[] split = src.split("'");
            for(int i=0;i<split.length;i++){
                Map map = new HashMap();
                map.put(c.getCommentsId(),split[i]);
                mapList.add(map);
            }
        }
        Map map1 = MapUtil.mapCombine(mapList);

        modelAndView.addObject("commentsList",commentsList);
        modelAndView.addObject("map1",map1);

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

    //跳转订单列表页面
    @GetMapping("/orderlist")
    public ModelAndView toOrder(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) request.getSession().getAttribute("user");
        //获得给用户下的所有订单
        List<Order> orderList = orderService.findOrderByuid(user.getId());

        modelAndView.addObject("orderList",orderList);
        modelAndView.setViewName("supermarket/orderlist");
        return modelAndView;
    }
    //跳转到处理订单页面
    @GetMapping("/toupdate/{orderId}")
    public String toUpdate(@PathVariable Long orderId, ModelMap map){
        map.put("orderId",orderId);

        return "supermarket/updateorder";
    }

    //处理订单状态
    @PostMapping("/updateorder")
    public String updateOrder(Long orderId){
        Order order = orderService.findCommondityById(orderId);
        int iscon = order.getIscon();       //0未评价  1已评价 2已退款
        int isPayoff = order.getIsPayoff(); //0未支付  1已支付  2配送中  3已送达
        //更改订单状态
        if(isPayoff == 0){
            order.setIsPayoff(1);
        }else if(isPayoff == 1){
            //退款
            order.setIscon(2);
            //更改库存
            Order order1 = orderService.findCommoditynum(orderId);
            List<Commodity> commodities = order1.getCommodities();
            List<OrderList> orderLists = order1.getOrderLists();
            for(int i=0;i<commodities.size();i++){
                Commodity commodity = commodities.get(i);
                int commodityStock = commodity.getCommodityStock();
                int commoditynum = orderLists.get(i).getCommoditynum();
                commodity.setCommodityStock(commodityStock+commoditynum);
                commodityService.update(commodity);
            }

        }
        orderService.update(order);

        return "redirect:/supermarket/orderlist";
    }

    //跳转结算订单页面
    @GetMapping("/order")
    public ModelAndView Order(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();

        Map<Commodity,Integer> car = (Map<Commodity, Integer>) session.getAttribute("car");
        Double sum = (Double) session.getAttribute("sum");
        Integer num = (Integer) session.getAttribute("num");
       /* modelAndView.addObject("map",map);
        modelAndView.addObject("sum",session.getAttribute("sum"));*/
       if(car != null){
           session.setAttribute("order",car);
       }
       if(sum != null){
           session.setAttribute("ordersum",sum);
       }
       if(num != null){
           session.setAttribute("ordernum",num);
       }

        //删除购物车数据
        session.removeAttribute("car");
        session.removeAttribute("num");
        session.removeAttribute("sum");

        modelAndView.setViewName("supermarket/order");
        /*modelAndView.addObject("orderId",order.getOrderId());*/
        return modelAndView;
    }

    //处理订单车
    @PostMapping("/ordercar/{id}")
    @ResponseBody
    public String ordercar(@PathVariable Long id,String state,HttpServletRequest request){
        Commodity commodity = commodityService.getById(id);
        HttpSession session = request.getSession();
        Map<Commodity,Integer> order = (Map<Commodity, Integer>) session.getAttribute("order");

        //便利map中的商品对象
        Iterator<Commodity> it = order.keySet().iterator();
        boolean f = true;

        //如果是添加商品
        if("add".equals(state)){
            //如果购物车存在相同id商品则在数量上加1
            while (it.hasNext()) {
                Commodity c = (Commodity)it.next();
                if (c.getCommodityId().equals(commodity.getCommodityId())) {
                    order.put(c, order.get(c)+1);
                    f = false;
                }
            }
            if(f){
                order.put(commodity,1);
            }

        }
        //如果是减少商品
        else if("minus".equals(state)){
            while (it.hasNext()){
                Commodity c = (Commodity)it.next();
                if(c.getCommodityId().equals(commodity.getCommodityId())){
                    Integer num = order.get(c);
                    if(num > 1){
                        order.put(c,order.get(c)-1);
                    }else{
                        order.remove(c);
                    }
                }
            }
        }
        session.setAttribute("order", order);

        //获取购物车中商品数量，和总价
        Integer num = 0;
        Double sum = 0.0;
        for (Map.Entry<Commodity,Integer> entry:order.entrySet()
                ) {
            Commodity commodity1 = (Commodity) entry.getKey();
            Integer n = entry.getValue();
            Double price = commodity1.getCommodityPrice();
            Double s = n*price;
            num = num+n;
            sum = sum+s;
        }
        session.setAttribute("ordernum",num);
        session.setAttribute("ordersum",sum);

        return "success";
    }


    //支付订单
    @PostMapping("/payoff")
    @ResponseBody
    public Long payoff(HttpServletRequest request,String conTime){
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();
        Map<Commodity,Integer> orderMap = (Map<Commodity, Integer>) request.getSession().getAttribute("order");
        Integer ordernum = (Integer) request.getSession().getAttribute("ordernum");
        Double ordersum = (Double) request.getSession().getAttribute("ordersum");
        User user = (User) session.getAttribute("user");
        Module module = (Module) session.getAttribute("module");

        //插入订单
        Order order = new Order();
        order.setTotalPrice(ordersum);
        order.setUser(user);
        order.setSum(ordernum);
        order.setModule(module);
        order.setSubmitTime(new Date());

        orderService.insertOrder(order);

        //插入订单商品关系表
        Long order_Id = order.getOrderId();
        for (Map.Entry<Commodity,Integer> entry:orderMap.entrySet()
                ) {
            Commodity commodity = (Commodity) entry.getKey();

            //插入ordelist表
            Integer commoditynum = entry.getValue();
            Long commodity_Id = commodity.getCommodityId();
            Map m = new HashMap<>();
            m.put("commoditynum",commoditynum);
            m.put("commodity_Id",commodity_Id);
            m.put("order_Id",order_Id);
            orderService.saveRelation(m);

            //更改库存
            int stock = commodity.getCommodityStock() - commoditynum;
            commodity.setCommodityStock(stock);
            commodityService.update(commodity);

        }


        return order_Id;
    }

    //跳转评价页面
    @GetMapping("/eval/{orderId}")
    public ModelAndView toEval(@PathVariable Long orderId,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        request.getSession().setAttribute("orderId",orderId);
        modelAndView.setViewName("supermarket/eval");
        return modelAndView;
    }

    //添加评价
    @PostMapping("/eval")
    @ResponseBody
    public String eval(@RequestBody Comments comments,HttpServletRequest request){

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Long orderId = (Long) session.getAttribute("orderId");
        Module module = orderService.findCommondityById(orderId).getModule();

        Order order = orderService.findCommondityById(orderId);
        order.setIscon(1);
        orderService.update(order);

        comments.setDateTime(new Date());
        comments.setUser(user);
        comments.setModule(module);

        commentsService.insert(comments);

        session.removeAttribute("orderId");
        return "success";
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

        //获取购物车中商品数量，和总价
        Integer num = 0;
        Double sum = 0.0;
        for (Map.Entry<Commodity,Integer> entry:car.entrySet()
             ) {
            Commodity commodity1 = (Commodity) entry.getKey();
            Integer n = entry.getValue();
            Double price = commodity1.getCommodityPrice();
            Double s = n*price;
            num = num+n;
            sum = sum+s;
        }
        session.setAttribute("num",num);
        session.setAttribute("sum",sum);

        return "success";
    }

}
