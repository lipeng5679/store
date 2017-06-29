package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Lipeng on 2017/6/26.
 */
@Controller
@Slf4j
public class HomeController {

    //首页
    @GetMapping("/")
    public ModelAndView toIndex(){
        return new ModelAndView("index");
    }
}
