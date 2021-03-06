package com.example.demo.controller;

import com.example.demo.domain.City;
import com.example.demo.domain.Scroll;
import com.example.demo.domain.User;
import com.example.demo.domain.Village;
import com.example.demo.service.*;
import com.example.demo.util.MapUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;


/**
 * Created by Lipeng on 2017/6/26.
 */
@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;
    @Autowired
    private ScrollService scrollService;
    @Autowired
    private ModuleService moduleService;

    @Autowired
    DefaultKaptcha defaultKaptcha;


    //跳转注册页面
    @GetMapping("/register")
    public ModelAndView loginUI() {
        return new ModelAndView("user/register");
    }

    //注册
    @PostMapping
    public ModelAndView login(HttpServletRequest request, User user) {

        ModelAndView andView = new ModelAndView();
        //验证码是否正确
        String captchaId = (String) request.getSession().getAttribute("vrifyCode");
        String parameter = request.getParameter("vrifyCode");
        System.out.println("Session  vrifyCode " + captchaId + " form vrifyCode " + parameter);

        if (!captchaId.equals(parameter)) {
            andView.addObject("info", "错误的验证码");
            andView.setViewName("user/register");
        } else {
            if (userService.getPhonenumber(user.getPhonenumber()) == null) {
                user.setDate(new Date());
                userService.insert(user);
                andView.setViewName("success");
            } else {
                andView.addObject("info", "已经存在该手机账号");
                andView.setViewName("user/register");
            }


        }

        return andView;
    }

    //生成验证码
    @GetMapping("/getMykaptcha")
    public void getMykaptcha(HttpServletRequest request,
                             HttpServletResponse response) throws Exception {

        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute("vrifyCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("Img/jpeg");
        ServletOutputStream responseOutputStream =
                response.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();

    }

    //跳转到登陆页面
    @GetMapping("/login")
    public ModelAndView tologin() {
        return new ModelAndView("user/login");
    }

    //登陆
    @PostMapping("/login")
    public ModelAndView login(String phonenumber, String password,HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getnumberAndpassword(phonenumber, password);
        if (user != null) {
            modelAndView.setViewName("redirect:/index");
            modelAndView.addObject("user",user);
            modelAndView.addObject("imgList",scrollService.findAll());
            modelAndView.addObject("moduleList",moduleService.findAll());

            request.getSession().setAttribute("user",user);
            return modelAndView;
        }else {
            modelAndView.addObject("info", "用户名或密码错误");
            modelAndView.setViewName("user/login");
        }


        return modelAndView;
    }

    //跳转找回密码页面
    @GetMapping("/back")
    public ModelAndView toBackPassword() {
        return new ModelAndView("user/back");
    }

    //找回密码并设置密码
    @PostMapping("/setPassword")
    public ModelAndView setPassword(HttpServletRequest request, String phonenumber, String password) {
        ModelAndView andView = new ModelAndView();
        //验证码是否正确
        String captchaId = (String) request.getSession().getAttribute("vrifyCode");
        String parameter = request.getParameter("vrifyCode");
        System.out.println("Session  vrifyCode " + captchaId + " form vrifyCode " + parameter);

        if (!captchaId.equals(parameter)) {

            andView.addObject("info", "验证码出错");
            andView.setViewName("user/back");
        } else {
            User user = userService.getPhonenumber(phonenumber);
            if (user == null) {
                andView.addObject("info", "不存在该手机账户");
                andView.setViewName("user/back");
            } else {
                user.setPassword(password);
                user.setDate(new Date());
                userService.update(user);
                andView.setViewName("backsuccess");
            }


        }

        return andView;
    }

    //跳转到选择小区页面并准备数据
    @GetMapping("/city")
    public ModelAndView toCity(){
        ModelAndView andView = new ModelAndView();
        List<City> cityList = cityService.findAll();
        List<Map> mapList = new ArrayList<>();

        for (City c:cityList
             ) {
            City city = cityService.findById(c.getC_id());
            Map map = new HashMap();
            if(city != null){
                map.put(city.getPing(),city);
                mapList.add(map);
            }
        }
        Map map1 = MapUtil.mapCombine(mapList);

        andView.addObject("map1",map1);
        andView.setViewName("user/city");

        return andView;
    }


}
