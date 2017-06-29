package com.example.demo.mapper;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/16.
 */
@Component
@Mapper
public interface UserMapper {

    /**
     * 查询所有
     * @return
     */
    List<User> findAll();

    /**
     * 查询一条
     * @return
     */
    User get(Serializable id);

    /**
     * 新增
     * @param user
     */
    void insert(User user);

    /**
     * 修改
     * @param
     */
    void update(User user);

    /**
     * 删除一条
     * @param id
     */
    void deleteById(Serializable id);

    /**
     * 删除多条
     * @param ids
     */
    void delete(Serializable[] ids);

    /**
     * 根据用户名和密码
     */
    User getnumberAndpassword(@Param("phonenumber") String phonenumber, @Param("password") String password);

    /**
     * 根据手机号
      * @param phonenumber
     * @return
     */
    User getPhonenumber(Serializable phonenumber);
}
