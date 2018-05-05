package cn.com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by fangzy on 2018/3/30 15:30
 */
@RestController
public class ConfigController {

    @RequestMapping("/")
    public String config() {
        return "config .....";
    }
}
