package com.itheima.health.controller;

import com.itheima.health.common.MessageConst;
import com.itheima.health.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author zhangmeng
 * @description
 * @date 2019/9/6
 **/
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    /**
     * 获取当前登录用户名
     *
     * @return
     */
    @GetMapping("/getUsername")
    public Result getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (null != principal && principal instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) principal;
            return new Result(true, MessageConst.GET_USERNAME_SUCCESS, user.getUsername());
        }
        return new Result(false, MessageConst.GET_USERNAME_FAIL);
    }
    @RequestMapping("/loginSuccess")
    public  Result loginSuccess(){
        return new Result(true, MessageConst.LOGIN_SUCCESS);
    }
    @RequestMapping("/loginFail")
    public Result loginFail(){
        return new Result(false,"登录失败");
    }

}
