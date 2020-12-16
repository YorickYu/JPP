package com.yy.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.GregorianCalendar;

public class Myjob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("job test");

        JobDetail jobDetail = context.getJobDetail();
        JobKey key = jobDetail.getKey();
        System.out.println(key.getName());
        System.out.println(key.getGroup());

    }



    public static void main(String[] args) throws SchedulerException {


        // Quartz Quick Start Guide
        // http://www.quartz-scheduler.org/documentation/quartz-2.3.0/quick-start.html#download-and-install

        // 1 调度器
        Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();

        // 2 触发器 - cron
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger", "trigger").withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?"))
                .build();

        // 2 触发器
//        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
//                .startNow()
//                .withIdentity("trigger", "trigger").withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).withRepeatCount(10))
//                .endAt(new GregorianCalendar(2020, 11, 24).getTime())
//                .build();

        // 3 job detail
        JobDetail jobDetail = (JobDetail) JobBuilder.newJob(Myjob.class).withIdentity("builder", "builder").build();

        // 4 调度器管理触发器和job detail
        defaultScheduler.scheduleJob(jobDetail, cronTrigger);

        // 5 启动
        defaultScheduler.start();
    }
}
