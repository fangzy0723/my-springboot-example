package cn.com.example.controller;

import cn.com.example.domain.UserLombok;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fangzy on 2018/4/13 9:54
 */
@RestController
@Slf4j
public class UserLomBokController {

    @RequestMapping("/online/getUserLombok")
    public UserLombok getUserLombok(){
        UserLombok userLombok = new UserLombok();
        userLombok.setAge(20);
        userLombok.setName("lisa");
        userLombok.setSex("M");
        log.info(userLombok.toString());
        return userLombok;
    }
}
