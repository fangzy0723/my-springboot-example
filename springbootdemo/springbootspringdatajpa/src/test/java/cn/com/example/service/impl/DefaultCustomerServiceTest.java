package cn.com.example.service.impl;

import cn.com.example.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    @Test
    public void findById() {
        customerService.findById(1);
    }

    @Test
    public void save() {
        customerService.save();
    }

    @Test
    public void update() {
        customerService.update(6);
    }

    @Test
    public void deleteById() {
        customerService.deleteById(6);
    }

    @Test
    public void findByName() {
        customerService.findByName("zhangsan");
    }

    @Test
    public void findByNameByQuerySQL() {
        customerService.findByNameByQuerySQL("zhangsan");
    }

    @Test
    public void findByNameByQueryJPQL() {
        customerService.findByNameByQueryJPQL("zhangsan");
    }

    @Test
    public void findAllByIdSort() {
        customerService.findAllByIdSort("id");
    }

    @Test
    public void findAllPage() {
        customerService.findAllPage(1,2);
    }

    @Test
    public void findAllPageAndSort() {
        customerService.findAllPageAndSort("id",0,5);

    }

    @Test
    public void findByConditions1() {
        customerService.findByConditions1(3,"zhangsan");
    }

    @Test
    public void findByConditions2() {
        customerService.findByConditions2(3,"zhangsan");
    }

}