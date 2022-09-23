package com.study.user.timer;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TimeTask {

    @Async
//    @Scheduled(cron = "0/10 * * * * ?")
    public void testTimeTask() throws Exception{
        System.out.println("定时任务执行...");
    }
}
