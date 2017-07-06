package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
//商品分类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityClass implements Serializable{

    private Long commodityClassId;      //分类id
    private String commodityClassName;  //分类name

    private List<Commodity> commodities;

    public CommodityClass(String commodityClassName) {
        this.commodityClassName = commodityClassName;
    }
}
