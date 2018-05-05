package cn.com.example.advice;

import cn.com.example.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常处理
 * Created by fangzy on 2018/2/9 11:52
 * Controller层抛出的异常在这统一捕获处理，返回给用户统一格式的内容
 * 请求在响应返回中，如果有异常抛出会被@ControllerAdvice捕获
 */
@ControllerAdvice
public class ExceptionHandleController {

    //这个方法只处理controller抛出来的CustomException异常
    //@ExceptionHandler注解指定要处理的异常
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    //指定返回的状态码
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public Map<String,Object> handleCustomException(CustomException ce){
        Map<String,Object> map = new HashMap<>();
        map.put("message",ce.getMessage());
        return map;
    }
}
