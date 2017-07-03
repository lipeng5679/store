package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/3.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Img implements Serializable {

    private Long id;
    private String name;
    private String src;
}
