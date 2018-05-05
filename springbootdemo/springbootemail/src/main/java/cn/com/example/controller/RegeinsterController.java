package cn.com.example.controller;

/**
 * Created by fangzy on 2018/2/12 9:19
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模仿邮箱验证
 * Created by fangzy on 2017/9/23 23:19
 */
@RestController
public class RegeinsterController {

    /**
     * 获取请求参数
     * @param code
     */
    @GetMapping("/regeinsterController/{code}")
    public void regeinsterController(@PathVariable String code){
        System.out.println(code);
    }

}