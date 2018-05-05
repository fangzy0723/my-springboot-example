package cn.com.example.controller;

import cn.com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by fangzy on 2018/4/22 18:21
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getMemberService")
    public Map<String,String> getMember(){
       return orderService.getMember();
    }
}
