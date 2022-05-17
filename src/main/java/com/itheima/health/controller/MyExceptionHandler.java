package com.itheima.health.controller;

import com.itheima.health.common.MessageConst;
import com.itheima.health.entity.Result;
import com.itheima.health.exception.BusinessRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.ServletException;

/**
 * 统一的异常处理
 * 核心逻辑：
 * 1-首先匹配类
 * 2-匹配父类
 * 3-如果匹配到多个父类，则选择继承关系最近的父类
 */
@RestControllerAdvice(basePackages = {"com.itheima.health.controller"})
@Slf4j
public class MyExceptionHandler {
    /**
     * 服务端异常，直接提示操作失败
     */
    @ResponseStatus(HttpStatus.OK) // 返回的http状态为 200
    @ExceptionHandler({Exception.class})
    public Result exception(Exception e) {
        log.error("", e);
        //直接提示操作失败
        return new Result(false, MessageConst.ACTION_FAIL);
    }

    /**
     * 业务异常
     */
    @ResponseStatus(HttpStatus.OK) // 返回的http状态为 200
    @ExceptionHandler({BusinessRuntimeException.class})
    public Result businessRuntimeException(Exception e) {
        // 不同于其它异常，这表示一种正常的业务逻辑，不要打印error级别的日志
        log.info("", e);
        //使用e.getMessage()作为提示语
        return new Result(false, e.getMessage());
    }

    /**
     * 权限不足异常<br>
     * 当用户权限不足时，SpringSecurity框架会抛出AccessDeniedException<br>
     * 我们在异常处理类里进行统一处理
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler({
            AccessDeniedException.class //  权限不足
    })
    public Result accessDeniedException(Exception e) throws Exception {
        log.info("",e);
        //提示用户权限不足
        return new Result(false,"权限不足，请切换账号重试");
    }

    /**
     * 希望交给spring处理的异常，所以继续上抛
     */
    @ExceptionHandler({
            ServletException.class,                     // mediaType不匹配、method不匹配……
            HttpMessageConversionException.class,       //http body转换异常，@RequestBody的参数
            MethodArgumentNotValidException.class ,     //http请求缺少查询参数
            MethodArgumentTypeMismatchException.class   //http请求参数类型不匹配
    })
    public Result servletException(Exception e) throws Exception {
        //继续上抛，由spring处理
        throw e;
    }
}
