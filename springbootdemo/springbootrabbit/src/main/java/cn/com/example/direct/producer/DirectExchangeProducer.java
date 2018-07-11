package cn.com.example.direct.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by fangzy on 2018/7/11 20:44
 */
@Component
public class DirectExchangeProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMsgToDirectExchange(){
        System.out.println("发送消息");
        amqpTemplate.convertAndSend("directExchange","topic.asd","这是向direct类型的交换机发送的消息");
    }
}
