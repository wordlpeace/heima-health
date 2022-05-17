package com.itheima.health.dao;

import com.itheima.health.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：zhang
 * @date ：Created in 2019/11/20
 * @description ：用户DAO
 * @version: 1.0
 */
public interface UserDao {

    /**
     * 根据userName查询
     * @param username
     * @return
     */
    User selectByUsername(@Param("username") String username);
}
