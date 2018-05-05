package cn.com.example;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fangzy on 2018/2/5 9:46
 * 获取到配置文件中以springbootdemo开头的自定义属性
 * 属性付默认值，如果配置文件中没有该自定义属性则使用默认值
 * 优先级 ：配置文件中的值大于默认值
 */
@Configuration
@ConfigurationProperties(prefix = "springbootdemo")
public class PropertiesConfig {

    private String name="asd";
    private String age="20";

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
