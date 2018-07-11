package cn.com.example.topic.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by fangzy on 2018/7/11 20:27
 */
@Component
@RabbitListener(queues = "queueMsga2")
public class TopicExchangeConsumer2 {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("从消息队列中获取到的消息内容2  : " + msg);
    }
}
