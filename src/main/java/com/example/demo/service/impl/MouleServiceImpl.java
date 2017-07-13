package com.example.demo.service.impl;

import com.example.demo.domain.City;
import com.example.demo.domain.Module;
import com.example.demo.mapper.CityMapper;
import com.example.demo.mapper.ModuleMapper;
import com.example.demo.service.CityService;
import com.example.demo.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
@Service
public class MouleServiceImpl implements ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;


    @Override
    public List<Module> findAll() {
        return moduleMapper.findAll();
    }

    @Override
    public Module getModuleById(Serializable moduleId) {
        return moduleMapper.getModuleById(moduleId);
    }

    @Override
    public List<Module> findAllCommentsByModuleId(Serializable moduleId) {
        return moduleMapper.findAllCommentsByModuleId(moduleId);
    }
}
