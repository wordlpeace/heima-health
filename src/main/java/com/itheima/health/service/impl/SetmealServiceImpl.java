package com.itheima.health.service.impl;

import com.itheima.health.dao.Setmealdao;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private Setmealdao setmealdao;

    @Override
    public void add(Setmeal setmeal) {
        setmealdao.insert(setmeal);
    }
}
