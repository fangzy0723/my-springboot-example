package cn.com.example.simple.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by fangzy on 2018/7/11 16:54
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMQProducerTest {

    @Autowired
    RabbitMQProducer rabbitMQProducer;

    @Test
    public void sendMessage() throws Exception {
        rabbitMQProducer.sendMessage();
    }

    @Test
    public void sendUser() throws Exception {
        rabbitMQProducer.sendUser();
    }

}