package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */
//城市
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City implements Serializable{

    private Long c_id;
    private String cityname;
    private String ping;
    private List<Village> villages;
}
