package com.example.demo.controller;

import com.example.demo.service.QiniuUploadService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/7/2.
 */
@Controller
@Slf4j
public class QiniuController {

    @Autowired
    private QiniuUploadService qiniuUploadService;

    @GetMapping("/upload")
    public String toUpload(){
        return "admin/upload";
    }

    @GetMapping("/token")
    @ResponseBody
    public String getUploadToken(){
        log.info("request token");
        String token = this.qiniuUploadService.getUploadToken();

        return token;
    }
}
