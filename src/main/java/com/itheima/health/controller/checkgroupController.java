package com.itheima.health.controller;

import com.itheima.health.common.MessageConst;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.service.checkgroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/checkgroup")
public class checkgroupController {
    @Autowired
    private checkgroupService checkgroupService;

    @RequestMapping("/findPage")
    public Result page(QueryPageBean queryPageBean){
        log.info("检查组,分页查询{}",queryPageBean);
        PageResult pageResult = checkgroupService.pageQuery(queryPageBean);
        return new Result(true, MessageConst.ACTION_SUCCESS,pageResult);
    }

 @RequestMapping("add")
    public Result add(String checkitemIds ,@RequestBody CheckGroup checkGroup ){
        checkgroupService.add(checkGroup);
       CheckGroup byName = checkgroupService.findByName(checkGroup.getName());
     Integer id = byName.getId();
     String[] split = checkitemIds.split(",");
     for (String s : split) {
        checkgroupService.InsertIntoRelationalTable(Integer.valueOf(s), id);
     }
     return new Result(true,MessageConst.ACTION_SUCCESS);
 }

 @RequestMapping("/findById")
    public Result findBuyid(Integer id) {
        log.info("检查组根据id查询");
     CheckGroup checkGroup = checkgroupService.findbyId(id);
     return new Result(true,MessageConst.ACTION_SUCCESS,checkGroup);
 }

 @RequestMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(Integer id) {
        log.info("根据检查组id查询检查项");
     List<Integer> checkItemIdsByCheckGroupId = checkgroupService.findCheckItemIdsByCheckGroupId(id);
     return new Result(true,MessageConst.ACTION_SUCCESS,checkItemIdsByCheckGroupId);
 }


}
