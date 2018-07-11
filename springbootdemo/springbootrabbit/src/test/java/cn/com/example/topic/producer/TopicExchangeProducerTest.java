package cn.com.example.topic.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by fangzy on 2018/7/11 20:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicExchangeProducerTest {
    @Autowired
    private TopicExchangeProducer topicExchangeProducer;

    @Test
    public void sendMsgToTopicExchange1() throws Exception {
        topicExchangeProducer.sendMsgToTopicExchange1();
    }

    @Test
    public void sendMsgToTopicExchange2() throws Exception {
        topicExchangeProducer.sendMsgToTopicExchange2();
    }

    @Test
    public void sendMsgToTopicExchange3() throws Exception {
        topicExchangeProducer.sendMsgToTopicExchange3();
    }
}