package com.itheima.health.job;

import com.itheima.health.common.RedisConst;
import com.itheima.health.service.SetMealService;
import com.itheima.health.util.QiniuUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Set;

@Slf4j
public class ClearImageJob extends QuartzJobBean {
    // 清理前最小等待时间
    private static final Long MIN_DELETE_WAIT_TIME = 24*60*60*1000L;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private QiniuUtils qiniuUtils;

    @Autowired
    private SetMealService setMealService;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("[清理垃圾图片]开始……");
        //清理垃圾图片
        //计算redis中两个集合的差值，获取垃圾图片名称
        Set<String> set = redisTemplate.boundSetOps(RedisConst.SETMEAL_PIC_RESOURCES).members();
        for (String img : set) {
            Long createTime = Long.valueOf(img.split("_",2)[0]);
            // 判断1:等待时间是否大于清理前的最小等待时间；防止用户表单未提交，图片就被删除了
            if(System.currentTimeMillis()-createTime<MIN_DELETE_WAIT_TIME){
                log.info("[清理垃圾图片]等待时间小于清理前的最小等待时间，暂不清除:{}",img);
                continue;
            }

            // 判断2：判断是否被使用(操作数据库和操作redis不在一个事务内，所有有可能：表单已提交，redis中数据并未删除)
            long count = setMealService.countByImg(img);
            if(count == 0){
                // 如果未被使用，则从七牛云删除
                log.info("[清理垃圾图片]{}",img);
                //从七牛云移除
                try {
                    qiniuUtils.deleteFileFromQiniu(img);
                } catch (RuntimeException e) {
                    log.error("[清理垃圾图片]失败:" + img, e);
                    continue;
                }

            }else{
                // 如果已经被使用，则无需从七牛云删除
                log.info("[清理垃圾图片]图片已使用，无需清理:{}",img);
            }
            // 无论是否垃圾图片，都从redis移除
            redisTemplate.boundSetOps(RedisConst.SETMEAL_PIC_RESOURCES).remove(img);
        }
        log.info("[清理垃圾图片]完成……");
    }
}