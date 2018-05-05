package cn.com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fangzy on 2018/4/15 10:11
 */
@Controller
public class PageController {

    @RequestMapping("/loginPage.html")
    public String toLoginPage(){
        return "/login";
    }

    @RequestMapping("/toSuccess.html")
    public String toSuccess(){
        return "/success";
    }

    @RequestMapping("/toFail.html")
    public String toFail(){
        return "/fail";
    }
}
