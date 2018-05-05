package cn.com.example.controller;

/**
 * Created by fangzy on 2018/2/9 17:22
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SpringBootScheduledController {
    /**
     *每20秒执行一次
     */
    @Scheduled(cron="0/20 * * * * ?")
    public void methodScheduling(){
        System.out.println("methodScheduling定时任务启动。。。。");
    }
}
