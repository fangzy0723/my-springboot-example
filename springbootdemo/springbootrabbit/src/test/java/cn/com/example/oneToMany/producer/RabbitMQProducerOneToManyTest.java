package cn.com.example.oneToMany.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by fangzy on 2018/7/11 19:51
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMQProducerOneToManyTest {

    @Autowired
    private RabbitMQProducerOneToMany rabbitMQProducerOneToMany;

    @Test
    public void sendMsg() throws Exception {
        rabbitMQProducerOneToMany.sendMsg();
    }

}