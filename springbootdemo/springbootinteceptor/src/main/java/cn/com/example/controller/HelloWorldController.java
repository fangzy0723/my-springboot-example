package cn.com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fangzy on 2018/2/28 10:01
 */
@RestController
public class HelloWorldController {

    @GetMapping("/helloWorld")
    public String helloWorld(){
        return "helloWorld";
    }
    @GetMapping("/getName")
    public String getName(){
        return "getName";
    }
}
