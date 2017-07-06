package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/6.
 */

//商品信息
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commodity implements Serializable {

    private Long commodityId;
    private String commodityName;       //名字
    private String commodityDescribe;   //描述
    private int commodityPrice;         //单价
    private int commodityStock;         //库存
    private String commoditySrc;        //缩略图
    private int state = 1;              //状态 0:伪删除 1:下架 2：上架

    private CommodityClass commodityClass;

    public Commodity(String commodityName, String commodityDescribe, int commodityPrice, int commodityStock, String commoditySrc, int state) {
        this.commodityName = commodityName;
        this.commodityDescribe = commodityDescribe;
        this.commodityPrice = commodityPrice;
        this.commodityStock = commodityStock;
        this.commoditySrc = commoditySrc;
        this.state = state;
    }
}
