package com.itheima.health.controller;

import com.itheima.health.common.MessageConst;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import com.itheima.health.service.checkgroupService;
import com.itheima.health.service.impl.QiniuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private QiniuService qiniuService;

    @RequestMapping("/add")
    public Result add(String checkgroupIds ,@RequestBody Setmeal Setmeal){
        log.info("套餐添加");
        setmealService.add(Setmeal,checkgroupIds);
        return new Result(true,MessageConst.ACTION_SUCCESS);
    }
    @RequestMapping("upload")
    public Result upload(@RequestParam("imgFile") MultipartFile file, HttpServletRequest request){
        if(file.isEmpty()) {
            return new Result(false, MessageConst.PIC_UPLOAD_FAIL);
        }
        try {
            String imageUrl = qiniuService.saveImage(file);
            return new Result(true,MessageConst.PIC_UPLOAD_SUCCESS,imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(false,MessageConst.PIC_UPLOAD_FAIL);
    }

    @RequestMapping("/findPage")
    public Result page(QueryPageBean queryPageBean){
        log.info("检查组,分页查询{}",queryPageBean);
        PageResult pageResult = setmealService.pageQuery(queryPageBean);
        return new Result(true, MessageConst.ACTION_SUCCESS,pageResult);
    }
}
