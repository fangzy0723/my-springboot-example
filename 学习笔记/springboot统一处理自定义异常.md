---
title: spring boot统一处理自定义异常
---

> demo测试通过，使用版本如下：
> spring boot版本：1.5.10.RELEASE
> java版本：1.8
> maven版本：apache-maven-3.3.9
> http请求到controller，在controller抛出一个自定义异常，响应返回过程中会被@ControllerAdvice注解的类拦截到，在@ControllerAdvice注解的类中统一处理返回给用户。用法参考代码如下：

- #### controller类

```
/**
 * 测试统一异常处理
 * Created by fangzy on 2018/2/9 11:40
 */
@RestController
public class CustomerExceptionController {

    @GetMapping("/customerException")
    public String getUser(){
        throw new CustomException("在这抛出一个自定义异常");
    }
}
```

<!--more-->

- ### 自定义异常类

```
/**
 * 自定义异常
 * Created by fangzy on 2018/2/9 11:48
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
```

- ### 处理异常的类

```
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
```

