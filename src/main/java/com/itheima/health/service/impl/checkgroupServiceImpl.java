package com.itheima.health.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.CheckItemDao;
import com.itheima.health.dao.checkgroupdao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.service.checkgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class checkgroupServiceImpl implements checkgroupService {
    @Autowired
    private checkgroupdao checkgroupdao;
    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {

        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        Page<CheckGroup> checkGroups = checkgroupdao.selectByConditon(queryPageBean.getQueryString());

        return new PageResult(checkGroups.getTotal(),checkGroups.getResult());

    }

    @Override
    public void add(CheckGroup checkGroup) {
        checkgroupdao.insert(checkGroup);
    }

    @Override
    public CheckGroup findByName(String name) {
        CheckGroup byName = checkgroupdao.findByName(name);
        return byName;
    }

    @Override
    public void InsertIntoRelationalTable(Integer checkitemIds ,Integer checkgroupid) {
        checkgroupdao.InsertIntoRelationalTable(checkitemIds,checkgroupid);
    }

    @Override
    public CheckGroup findbyId(Integer id) {
        CheckGroup byId = checkgroupdao.findById(id);
        return byId;
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        List<Integer> checkItemIdsByCheckGroupId = checkgroupdao.findCheckItemIdsByCheckGroupId(id);
        return checkItemIdsByCheckGroupId;
    }
}
