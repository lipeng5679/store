package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Lipeng on 2017/6/26.
 */
//用户
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Long id;            //主键
    private String phonenumber;    //手机号
    private String nickname;    //昵称
    private String xiaoqu;      //所在小区
    private String name;        //姓名
    private String address;     //地址
    private String password;    //密码
    private Date date;          //注册时间

    private List<Order> orders;    //订单


}
