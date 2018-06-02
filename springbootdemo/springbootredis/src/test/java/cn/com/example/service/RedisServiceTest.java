package cn.com.example.service;

import cn.com.example.domain.User;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    StringRedisTemplate stringRedisTemplate;

    /**
     *  字符串（strings）: stringRedisTemplate.opsForValue()
     *  散列（hashes）： stringRedisTemplate.opsForHash()
     *  列表（lists）：stringRedisTemplate.opsForList()
     *  集合（sets）：stringRedisTemplate.opsForSet()
     *  有序集合（sorted sets）：stringRedisTemplate.opsForZSet()
     * @throws Exception
     */

    @Test
    public void testStringRedisTemplate() throws Exception {
        stringRedisTemplate.opsForValue().set("age","22");
        System.out.println(stringRedisTemplate.opsForValue().get("age"));
    }
    @Test
    public void testredisTemplate() throws Exception {
        redisTemplate.opsForValue().set("user",new User(1,"fangzy",20));
        System.out.println(redisTemplate.opsForValue().get("user"));
    }



}