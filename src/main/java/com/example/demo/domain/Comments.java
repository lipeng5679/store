package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/11.
 */
//评价
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments implements Serializable{

    private Long commentsId;
    private Date dateTime;       //评论时间
    private String content;     //内容
    private String src;         //评论图片


    private User user;
    private Module module;      //模块
}
