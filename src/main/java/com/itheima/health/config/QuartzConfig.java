package com.itheima.health.config;

import com.itheima.health.job.ClearImageJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail clearImageJobDetail(){
        return JobBuilder.newJob(ClearImageJob.class)//PrintTimeJob我们的业务类
                         .withIdentity("clearImageJobDetail")//可以给该JobDetail起一个id
                         .storeDurably(true)
                         .build();
    }
    @Bean
    public Trigger printTimeJobTrigger(@Qualifier("clearImageJobDetail") JobDetail jobDetail) throws ParseException {
        return TriggerBuilder.newTrigger()
                             .forJob(jobDetail)//关联上述的JobDetail
                             .withIdentity("clearImageJobTrigger")//给Trigger起个名字
//                             for test
//                             .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))// 设置定时执行的规律
//                             for online
                             .withSchedule(CronScheduleBuilder.cronSchedule("0 0 2 * * ?"))// 设置定时执行的规律
                             .build();
    }
}