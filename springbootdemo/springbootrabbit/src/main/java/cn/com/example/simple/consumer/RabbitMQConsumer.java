package cn.com.example.simple.consumer;

import cn.com.example.simple.domain.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by fangzy on 2018/7/11 16:51
 */
@Component
@RabbitListener(queues = "hello123")
public class RabbitMQConsumer {


    @RabbitHandler
    public void process(User user) {
        System.out.println("从消息队列中获取到对象的消息内容  : " + user);
    }
}
