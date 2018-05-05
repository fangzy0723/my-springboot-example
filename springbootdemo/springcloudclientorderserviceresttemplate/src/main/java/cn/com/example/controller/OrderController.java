package cn.com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by fangzy on 2018/4/22 13:33
 */
@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/order/getMember")
    public Map<String,String> getMember(){
        System.out.println("使用restTemplate提供的方法获取服务提供者提供的服务");
        return restTemplate.getForObject("http://member-service/member/getMemberService",Map.class);
    }

    @GetMapping("/order/getOrder")
    public String getOrder(){
        System.out.println("getOrder()方法被调用！");
        return "getOrder()方法被调用！";
    }
}
