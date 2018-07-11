package cn.com.example.oneToMany.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by fangzy on 2018/7/11 19:49
 */
@Component
@RabbitListener(queues = "one2many")
public class RabbitMQConsumerOneToMany2 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("从消息队列中获取到的消息内容2  : " + hello);
    }
}
