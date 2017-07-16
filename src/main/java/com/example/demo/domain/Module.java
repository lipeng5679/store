package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */
//功能模块
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Module implements Serializable{
    private long moduleId;
    private String src;
    private String name;
    private String url;


    private List<Comments> comments;     //评价

}
