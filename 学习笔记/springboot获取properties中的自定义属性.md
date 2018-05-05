

> demo测试通过，使用版本如下：
> spring boot版本：1.5.10.RELEASE
> java版本：1.8
> maven版本：apache-maven-3.3.9

#### 方法一 （使用@Value注解的方式）
- application.properties文件：
```
springbootdemo.name=fangzy
```
- java类：
```
/**
 * Created by fangzy on 2018/2/5 9:37
 * 使用@Value注解获取application.properties中的自定义注解
 */
@RestController
public class PropertiesValueController {
    @Value("${springbootdemo.name}")
    private String name;
    @RequestMapping("/getPropertiesByValueAnnotation")
    public String getPropertiesByValueAnnotation(){
        return name;
    }
}
```

#### 方法二 （使用配置类的方式）
- application.properties文件：
```
springbootdemo.name=fangzy
```
- 配置类：
```
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
	//省略get、set方法
}
```
- java类：
```
/**
 * Created by fangzy on 2018/2/5 9:47
 * 从配置类中获取配置文件中的自定义属性
 */
@RestController
public class PropertiesConfigController {
    @Autowired
    private PropertiesConfig propertiesConfig;
    @RequestMapping("/getPropertiesByConfigurationProperties")
    public String getPropertiesByConfigurationProperties(){
        return propertiesConfig.getName()+":"+propertiesConfig.getAge();
    }
}
```
#### 方法三  （使用配置类中引用对象的方式）
- application.properties文件：
```
springbootdemo.user.name=lisa
```
- 配置类：
```
/**
 * Created by fangzy on 2018/2/5 10:31
 * 对象属性名跟配置文件中一样
 */
@Configuration
@ConfigurationProperties(prefix = "springbootdemo")
public class SpringBootProperties {
    private UserPropertiesConfig user = new UserPropertiesConfig();
    //省略get、set方法
}
```

	```
	public class UserPropertiesConfig {
		private String name="asd";
		//省略get、set方法
	}
	```
- java类：
```
/**
 * Created by fangzy on 2018/2/5 9:47
 * 从配置类中获取配置文件中对象中的自定义属性
 */
@RestController
public class PropertiesConfigEntityController {
    @Autowired
    private SpringBootProperties springBootProperties;
    @RequestMapping("/getPropertiesByConfigurationEntityProperties")
    public String getPropertiesByConfigurationEntityProperties(){
        System.out.println(springBootProperties.getUser().getName());
        return springBootProperties.getUser().getName();
    }
}
```