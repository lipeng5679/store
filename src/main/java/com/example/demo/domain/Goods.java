package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/6.
 */

//订单
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {

    private Long goodsId;
}
