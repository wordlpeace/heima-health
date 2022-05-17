package com.itheima.health.controller;

import com.itheima.health.common.MessageConst;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetMealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mobile/setmeal")
@Slf4j
public class MobileSetMealController {

    @Autowired
    private SetMealService setMealService;

    /**
     * 查询套餐
     * @return
     */
    @GetMapping("/getSetmeal")
    public Result getSetmeal(){
        //rpc调用查询所有套餐
        List<Setmeal> setmeals = setMealService.findAll();
        return new Result(true, MessageConst.GET_SETMEAL_LIST_SUCCESS,setmeals);
    }
    @GetMapping("/findById")
    public Result findById(Integer id){
        //rpc调用查询数据
        Setmeal setmeal = setMealService.findById(id);
        //返回
        return new Result(true,MessageConst.QUERY_SETMEAL_SUCCESS,setmeal);
    }
}
