package com.example.demo.controller;

import com.example.demo.domain.*;
import com.example.demo.service.*;

import com.example.demo.util.MapUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lipeng on 2017/6/26.
 */
@Controller
@Slf4j
public class HomeController {

    @Autowired
    private ImgService imgService;
    @Autowired
    private ScrollService scrollService;
    @Autowired
    private CityService cityService;
    @Autowired
    private VillageService villageService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private CommentsService commentsService;

    //首页
    @GetMapping("/index")
    public ModelAndView toIndex(){
        ModelAndView modelAndView = new ModelAndView();
        List<Scroll> imgList = scrollService.findAll();
        List<Module> moduleList = moduleService.findAll();

        modelAndView.addObject("imgList",imgList);
        modelAndView.addObject("moduleList",moduleList);

        modelAndView.setViewName("index");

        return modelAndView;
    }

    @GetMapping("/")
    public ModelAndView toWelcome(){
        return new ModelAndView("welcome");
    }

    //跳转到图片选择页面
    @GetMapping("/img")
    public ModelAndView toImg(){
        ModelAndView modelAndView = new ModelAndView();
        List<Img> imgList = imgService.findAll();
        modelAndView.addObject("imgList",imgList);
        modelAndView.setViewName("admin/img");

        //获取以设置的滚动图片id
        List<Scroll> scrollList = scrollService.findAll();
        Long[] longs = new Long[scrollList.size()];
        for(int i=0;i<scrollList.size();i++){
            longs[i] = scrollList.get(i).getId();
        }

        modelAndView.addObject("longs",longs);


        return modelAndView;
    }


    //设置滚动图片
    @PostMapping ("/set")
    @ResponseBody
    public String set(String[] ids){

        scrollService.deleteAll();
        for (String s:ids
             ) {
            Img img = imgService.get(s);
            Scroll scroll = new Scroll();
            scroll.setSrc(img.getSrc());
            scroll.setId(img.getId());
            scrollService.insert(scroll);

        }

        return "index";
    }

    //七牛上传图片后保存数据库
    @GetMapping("/saveImg")
    @ResponseBody
    public String saveImg(String src){
        Img img = new Img();
        img.setSrc(src);
        imgService.insert(img);

        return "success";
    }


    //跳转到增加小区页面并准备数据
    @GetMapping("/admin/addcity")
    public ModelAndView toAddCity(){
        ModelAndView modelAndView = new ModelAndView();
        List<City> all = cityService.findAll();

        List<String> pings = new ArrayList<>();
        for (City c:all
             ) {
            pings.add(c.getPing());
        }
        modelAndView.addObject("pings",pings);
        modelAndView.setViewName("admin/addcity");

        return modelAndView;
    }

    //根据id查询小区
    @PostMapping("/getvillage")
    @ResponseBody
    public String getVillage(String id){
        List<Village> villages = villageService.getBycid(id);
        String json= JSONArray.fromObject(villages).toString();

        return json;
    }

    //根据首字母查城市
    @PostMapping("/getcity")
    @ResponseBody
    public String getCity(String ping){
        List<City> cities = cityService.getByCityping(ping);
        String json= JSONArray.fromObject(cities).toString();


        return json;
    }


    //保存新增小区信息
    @PostMapping("/addcity")
    @ResponseBody
    public ModelAndView addCity(City city,String villagename){

        City city1 = cityService.getByCityname(city.getCityname());
        if(city1 == null){
            cityService.insert(city);
        }

        if(villagename != null){
            Village village = new Village();
            village.setVillagename(villagename);
            village.setCid(city1.getC_id());
            villageService.insert(village);
        }

        return new ModelAndView("添加成功");
    }

    //跳转到后台管理主页
    @GetMapping("/admin")
    public String toadmin(){
        return "admin/index";
    }

    //跳转到全部评价列表页面
    @GetMapping("/evallist")
    public String toevalList(ModelMap modelMap, HttpServletRequest request){
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

        modelMap.put("commentsList",commentsList);
        modelMap.put("map1",map1);
        return "evallist";
    }
}
