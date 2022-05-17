package com.itheima.health.config;

import com.itheima.health.security.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 关闭防CSRF攻击
        http .csrf().disable()
             // 设置登录相关配置
             .formLogin()
             .loginProcessingUrl("/sec/login") // 登录请求地址
             .failureForwardUrl("/user/loginFail") // 登录失败转地址
             .successForwardUrl("/user/loginSuccess") // 登录成功转发地址
             .loginPage("http://localhost:18080/pages/login.html") // 登录页地址
             // 设置登出相关配置
             .and().logout()
             .logoutUrl("/sec/logout") // 登出请求地址
             .logoutSuccessUrl("http://localhost:18080/pages/login.html"); // 登出成功后跳转地址
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 设置自定义的UserDetailService
        auth.userDetailsService(userDetailsService);
    }
}
