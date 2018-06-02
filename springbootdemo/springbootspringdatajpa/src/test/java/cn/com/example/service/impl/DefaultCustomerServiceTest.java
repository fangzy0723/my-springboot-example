package cn.com.example.service.impl;

import cn.com.example.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by fangzy on 2018/5/20 17:29
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DefaultCustomerServiceTest {
    @Autowired
    private CustomerService customerService;
    @Test
    public void findAll() throws Exception {
        customerService.findAll();
    }

}