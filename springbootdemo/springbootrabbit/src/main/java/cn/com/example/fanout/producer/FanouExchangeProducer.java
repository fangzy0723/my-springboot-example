package cn.com.example.fanout.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by fangzy on 2018/7/11 20:23
 */
@Component
public class FanouExchangeProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 测试发送消息到fanout类型的交换机
     */
    public void sendMsgToFanouExchang(){
        System.out.println("向fanout类型的交换机发送消息");
        amqpTemplate.convertAndSend("fanoutExchange","","这是一个发送到fanout类型交换机的消息");
    }

}
