package cn.com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by fangzy on 2018/4/22 13:06
 */
@RestController
public class MemberController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/member/getMemberService")
    public Map<String,String> getMemberService(){

        Map<String,String> map = new LinkedHashMap<>();
        map.put("name","zhangsan");
        map.put("date","2018-04-22");
        map.put("state","1");
        map.put("port",port);
        return map;
    }

    @GetMapping("/member/getMember")
    public String  getMember(){
        System.out.println("getMember()方法被调用！");
        return "getMember()方法被调用！";
    }

}
