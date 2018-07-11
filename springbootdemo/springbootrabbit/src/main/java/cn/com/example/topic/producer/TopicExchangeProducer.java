package cn.com.example.topic.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by fangzy on 2018/7/11 20:44
 */
@Component
public class TopicExchangeProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMsgToTopicExchange1(){
        amqpTemplate.convertAndSend("topicExchange","topic","这是想topic类型的交换机发送的消息1111");
    }

    public void sendMsgToTopicExchange2(){
        amqpTemplate.convertAndSend("topicExchange","topic.msg","这是想topic类型的交换机发送的消息2222");
    }

    public void sendMsgToTopicExchange3(){
        amqpTemplate.convertAndSend("topicExchange","topic.msg.aaa","这是想topic类型的交换机发送的消息3333");
    }
}
