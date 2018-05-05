package cn.com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fangzy on 2018/2/5 9:47
 * 从配置类中获取配置文件中的自定义属性
 */
@RestController
public class PropertiesConfigController {

    @Autowired
    private PropertiesConfig propertiesConfig;

    @RequestMapping("/getPropertiesByConfigurationProperties")
    public String getPropertiesByConfigurationProperties(){
        return propertiesConfig.getName()+":"+propertiesConfig.getAge();
    }

}
