package cn.com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fangzy on 2018/3/30 16:50
 */
@RestController
@RefreshScope
public class ConfigController {

    @Value("${config.hello}")
    private String configVal;

    @RequestMapping("/getConfig")
    public String getConfig(){
        return configVal;
    }
}
