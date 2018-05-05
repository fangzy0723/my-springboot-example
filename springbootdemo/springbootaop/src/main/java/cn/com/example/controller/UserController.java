package cn.com.example.controller;

import cn.com.example.anno.ResultBeans;
import cn.com.example.domain.ResultBean;
import cn.com.example.domain.User;
import cn.com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fangzy on 2018/2/9 15:41
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * controller的方法抛出异常时，aop处理的优先级比异常捕获的优先级高
     * @return
     */
    @ResultBeans
    @GetMapping("/getUser")
    public ResultBean getUser(){
        return new ResultBean<User>(userService.getUser());
    }
}
