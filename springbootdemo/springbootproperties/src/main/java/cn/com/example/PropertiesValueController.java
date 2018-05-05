package cn.com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fangzy on 2018/2/5 9:37
 * 使用@Value注解获取application.properties中的自定义注解
 */
@RestController
public class PropertiesValueController {

    @Value("${springbootdemo.name}")
    private String name;

    @RequestMapping("/getPropertiesByValueAnnotation")
    public String getPropertiesByValueAnnotation(){
        return name;
    }
}
