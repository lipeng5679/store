package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/29.
 */
//小区
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Village implements Serializable{

    private Long v_id;
    private String villagename;
    private City city;
}
