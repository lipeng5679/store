package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/3.
 */

//设置滚动图片
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scroll implements Serializable{

    private Long id;
    private String src;
    private String name;
}
