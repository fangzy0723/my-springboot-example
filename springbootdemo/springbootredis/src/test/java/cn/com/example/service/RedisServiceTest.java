package cn.com.example.service;

import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by fangzy on 2018/3/20 14:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisService redisService;

    @Test
    public void set() throws Exception {
        redisService.set("name","fangzy");
    }

    @Test
    public void set1() throws Exception {
    }

    @Test
    public void exists() throws Exception {
    }

    @Test
    public void get() throws Exception {
    }

    @Test
    public void remove() throws Exception {
    }

    @Test
    public void remove1() throws Exception {
    }

    @Test
    public void removePattern() throws Exception {
    }

    @Test
    public void hashSet() throws Exception {
    }

    @Test
    public void hashGet() throws Exception {
    }

    @Test
    public void push() throws Exception {
    }

    @Test
    public void range() throws Exception {
    }

    @Test
    public void setAdd() throws Exception {
    }

    @Test
    public void setMembers() throws Exception {
    }

    @Test
    public void zAdd() throws Exception {
    }

    @Test
    public void rangeByScore() throws Exception {
    }

}