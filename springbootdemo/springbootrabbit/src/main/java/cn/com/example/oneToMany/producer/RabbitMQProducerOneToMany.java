package cn.com.example.oneToMany.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by fangzy on 2018/7/11 19:40
 */
@Component
public class RabbitMQProducerOneToMany {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMsg(){
        for (int i = 0; i < 100; i++) {
            String ms = "这是第："+i+"条消息！";
            System.out.println(ms);
            this.amqpTemplate.convertAndSend("one2many",ms);
        }
    }

}
