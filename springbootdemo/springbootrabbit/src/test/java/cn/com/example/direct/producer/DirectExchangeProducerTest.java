package cn.com.example.direct.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by fangzy on 2018/7/11 21:06
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class DirectExchangeProducerTest {
    @Autowired
    private DirectExchangeProducer directExchangeProducer;
    @Test
    public void sendMsgToDirectExchange() throws Exception {
        directExchangeProducer.sendMsgToDirectExchange();
    }

}