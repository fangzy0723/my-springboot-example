package cn.com.example.controller;

import cn.com.example.domain.UserLombok;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.lang.reflect.Method;

/**
 * Created by fangzy on 2018/4/13 9:54
 */
@RestController
@Slf4j
public class UserLomBokController {

    @Autowired
    WebApplicationContext webApplicationContext;

    @GetMapping("/online/getUserLombok")
    public UserLombok getUserLombok(){

        UserLombok userLombok = new UserLombok();
        userLombok.setAge(20);
        userLombok.setName("lisa");
        userLombok.setSex("M");
        log.info(userLombok.toString());
        return userLombok;
    }

    @PostMapping("/online/getUserLombok")
    public UserLombok postUserLombok(String name,Integer age){

        UserLombok userLombok = new UserLombok();
        userLombok.setAge(age);
        userLombok.setName(name);
        userLombok.setSex("M");
        log.info(userLombok.toString());
        return userLombok;
    }


    public void methodHasAnnotation(){
        //获取ioc容器中所有的bean
        String[] beans =  webApplicationContext.getBeanDefinitionNames();
        Class<?> type;
        for (String bean:beans){
            //遍历ioc容器中的bean，通过反射获取到每个bean对应的Class
            type = webApplicationContext.getType(bean);
            //Class中获取这个类中的方法
            Method[] methods = type.getDeclaredMethods();
            for(Method method : methods){
                //遍历这个类中的方法，获取每个方法中RequestMapping的注解
                RequestMapping re = method.getAnnotation(RequestMapping.class);
                if(re != null){
                    System.out.println(method.getName()+" 方法中有注解RequestMapping ：");
                }else{
                    System.out.println(method.getName()+" 方法中没有注解RequestMapping");
                }
            }
        }
    }
}
