package com.example.demo.service.impl;

import com.example.demo.domain.Order;
import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return this.userMapper.findAll();
    }

    @Override
    public User get(Serializable id) {
        return this.userMapper.get(id);
    }

    @Override
    public void insert(User user) {
        this.userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        this.userMapper.update(user);
    }

    @Override
    public void deleteById(Serializable id) {
        this.userMapper.deleteById(id);
    }

    @Override
    public void delete(Serializable[] ids) {
        this.userMapper.delete(ids);
    }

    @Override
    public User getnumberAndpassword(String phonenumber,String password) {

        return userMapper.getnumberAndpassword(phonenumber,password);
    }

    @Override
    public User getPhonenumber(Serializable phonenumber) {
        return userMapper.getPhonenumber(phonenumber);
    }

    @Override
    public List<Order> findOrdersById(Serializable uid) {
        return userMapper.findOrdersById(uid);
    }
}
