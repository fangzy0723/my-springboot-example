package cn.com.example.service;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by fangzy on 2018/4/22 18:30
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Override
    public Map<String, String> getMember() {
        Map<String,String> map = new LinkedHashMap<>();
        map.put("name","member-service服务不可以！");
        return map;
    }
}
