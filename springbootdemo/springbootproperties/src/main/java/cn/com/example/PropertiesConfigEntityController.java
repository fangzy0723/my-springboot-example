package cn.com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fangzy on 2018/2/5 9:47
 * 从配置类中获取配置文件中对象中的自定义属性
 */
@RestController
public class PropertiesConfigEntityController {

    @Autowired
    private SpringBootProperties springBootProperties;

    @RequestMapping("/getPropertiesByConfigurationEntityProperties")
    public String getPropertiesByConfigurationEntityProperties(){
        System.out.println(springBootProperties.getUser().getName());
        return springBootProperties.getUser().getName();
    }

}
