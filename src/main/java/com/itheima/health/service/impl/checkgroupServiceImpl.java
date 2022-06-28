package com.itheima.health.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.checkgroupdao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.BusinessRuntimeException;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.checkgroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
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
        checkgroupdao.InsertIntoRelationalTable(checkgroupid,checkitemIds);
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

    @Override
    public void edit(String checkitemIds ,CheckGroup checkGroup) {
        checkgroupdao.edit(checkGroup);
        checkgroupdao.deleteBycheckGroup(checkGroup.getId());
        String[] split = checkitemIds.split(",");
        for (String s : split) {
        checkgroupdao.InsertIntoRelationalTable(checkGroup.getId(),Integer.valueOf(s));
        }
    }

    @Override
    public void delete(Integer id) {
        log.info("检查组删除");
        Integer integer = checkgroupdao.countCheckItemIdByCheckGroup(id);
        if (integer!=0)
        {
            throw new BusinessRuntimeException("当前检查组被引用,不可删除");
        }
        checkgroupdao.delete(id);
        checkgroupdao.Deleterelationtable(id);
    }

    @Override
    public List<CheckGroup> findall() {
        List<CheckGroup> all = checkgroupdao.findAll();
        return all;
    }
}
