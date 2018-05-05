package cn.com.example.controller;

import cn.com.example.exception.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试统一异常处理
 * Created by fangzy on 2018/2/9 11:40
 */
@RestController
public class CustomerExceptionController {

    @GetMapping("/customerException")
    public String getUser(){
        throw new CustomException("在这抛出一个自定义异常");
    }
}
