package cn.com.example.fanout.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by fangzy on 2018/7/11 20:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FanouExchangeProducerTest {

    @Autowired
    private FanouExchangeProducer fanouExchangeProducer;


    @Test
    public void sendMsgToFanouExchang() throws Exception {
        fanouExchangeProducer.sendMsgToFanouExchang();
    }

}