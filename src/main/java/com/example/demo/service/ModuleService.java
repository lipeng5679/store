package com.example.demo.service;

import com.example.demo.domain.City;
import com.example.demo.domain.Module;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface ModuleService {

    List<Module> findAll();

    Module getModuleById(Serializable moduleId);

    List<Module> findAllCommentsByModuleId(Serializable moduleId);

}
