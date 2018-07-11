package cn.com.example.simple.producer;

import cn.com.example.simple.domain.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by fangzy on 2018/7/11 16:43
 */
@Component
public class RabbitMQProducer {
    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendMessage(){
        String context = "向消息队列发送消息,时间： " + new Date();
        System.out.println("消息内容 : " + context);
        this.amqpTemplate.convertAndSend("hello123", context);
    }

    public void sendUser(){
        User user = new User(1,"fangzy",20,"中国");
        String context = "向消息队列发送对象： " + user;
        System.out.println("消息内容 : " + user);
        this.amqpTemplate.convertAndSend("hello123", user);
    }
}
