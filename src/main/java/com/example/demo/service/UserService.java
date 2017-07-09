package com.example.demo.service;

import com.example.demo.domain.Order;
import com.example.demo.domain.User;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface UserService {

    List<User> findAll();

    User get(Serializable id);

    void insert(User user);

    void update(User user);

    void deleteById(Serializable id);

    void delete(Serializable[] ids);

    User getnumberAndpassword(String phonenumber,String password);

    User getPhonenumber(Serializable phonenumber);

    List<Order> findOrdersById(Serializable uid);
}
