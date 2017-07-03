package com.example.demo.controller;

import com.example.demo.domain.Img;
import com.example.demo.domain.Scroll;
import com.example.demo.service.ImgService;
import com.example.demo.service.ScrollService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

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

    //首页
    @GetMapping("/index")
    public ModelAndView toIndex(){
        ModelAndView modelAndView = new ModelAndView();
        List<Scroll> imgList = scrollService.findAll();


        modelAndView.addObject("imgList",imgList);
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
        modelAndView.setViewName("img");

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
}
