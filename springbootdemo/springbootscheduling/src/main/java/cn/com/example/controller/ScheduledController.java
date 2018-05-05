package cn.com.example.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by fangzy on 2018/2/9 17:33
 */
@Component
public class ScheduledController {
    /**
     *每20秒执行一次
     */
    @Scheduled(cron="0/10 * * * * ?")
    public void methodScheduling(){
        System.out.println("methodScheduling定时任务启动了");
    }
}
