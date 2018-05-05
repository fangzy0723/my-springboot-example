---
title: spring boot 使用aop
---

> demo测试通过，使用版本如下：
> spring boot版本：1.5.10.RELEASE
> java版本：1.8
> maven版本：apache-maven-3.3.9
> 使用spring aop统一返回请求的响应格式
> 参考示例代码如下：

- ### pom文件引入web、aop的依赖

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

<!--more-->

- ### controller类
 ```
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
 ```
 
 - ### service及其实现类
 
 ```
 /**
 * Created by fangzy on 2018/2/9 16:15
 */
public interface UserService {
    public User getUser();
}
 ```
 ```
 /**
 * Created by fangzy on 2018/2/9 16:18
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUser() {
        User user = new User();
        user.setName("lisa");
        return user;
    }
}
 ```
 
 - ### 自定义注解,aop切面表达式用
 
 ```
 /**
 * 自定义注解，aop中切标有该注解的方法
 * Created by fangzy on 2018/2/9 15:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ResultBeans {
}
 ```
 
 - ### aop处理类
 
 ```
 /**
 * Created by fangzy on 2018/2/9 15:21
 * 类级注解：
 * @Aspect 声明这是一个切面对象
 * 方法级注解：
 * @Pointcut 声明一个切点规则
 * @Before 方法执行之前，执行通知。
 * @After 方法执行之后，不考虑其结果，执行通知。
 * @AfterReturning 方法执行之后，只有在方法成功完成时，才能执行通知
 * @AfterThrowing 方法执行之后，只有在方法退出抛出异常时，才能执行通知
 * @Around 在方法调用之前和之后，执行通知 等价于上面四个相加
 */
@Aspect
@Component
public class ResultBeansAdvice {
    Logger logger = LoggerFactory.getLogger(ResultBeansAdvice.class);

    /**
     * 切所有带有@ResultBeans注解的方法
     */
    @Pointcut("@annotation(cn.com.example.anno.ResultBeans)")
    private void businessService() {
    }
    /**
     * 环绕通知
     * @param pjp
     * @return
     */
    @Around("businessService()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp){
        long startTime = System.currentTimeMillis();
        ResultBean<?> result;
        try{
            result =(ResultBean<?>)pjp.proceed();
            result.setCode(ResultEnum.SUCCESS.getCode());
            result.setMessage(ResultEnum.SUCCESS.getMsg());
            logger.info("执行方法：{}  耗时: {}",pjp.getSignature(),(System.currentTimeMillis()-startTime));
        }catch(Throwable e){
            result =handlerException(pjp, e);
        }
        return result;
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint pjp,Throwable e){
        ResultBean<?> result = new ResultBean();
        if(e instanceof CustomException){
            CustomException CustomException = (CustomException)e;
            logger.info("这是个ResultBeansAdvice中的自定义异常");
            result.setMessage(CustomException.getMessage());
            result.setCode(CustomException.getCode());
        }else{
            logger.info("这是个ResultBeansAdvice中的未知异常");
            logger.error(pjp.getSignature()+" error ", e);
            result.setMessage(e.getMessage());
            result.setCode(ResultEnum.UNKONW_ERROR.getCode());
            // 未知异常是应该重点关注的，这里可以做其他操作，如通知邮件，单独写到某个文件等等。
        }
        return result;
    }
}
 ```
 
 - ### 自定义异常类
 
 ```
 /**
 * Created by fangzy on 2018/2/9 15:32
 */
public class CustomException extends RuntimeException {
    private Integer code;

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
    }
    //省略get，set方法
}
 ```
 
 - ### ResultBean类
 
 ```
 /**
 * Created by fangzy on 2018/2/9 15:26
 */
public class ResultBean<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;


    public ResultBean(Integer code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultBean() {
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.code = ResultEnum.ERROR.getCode();
        this.message = e.toString();
    }
    //省略get、set方法
}
 ```
 
 - ### 枚举类 
 
 ```
 /**
 * Created by fangzy on 2018/2/9 15:28
 */
public enum ResultEnum {
    UNKONW_ERROR(-1,"未知错误"),
    SUCCESS(1,"成功"),
    ERROR(0,"出错了");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    //省略get、set方法
}
 ```
 
 - ### User实体类
 
 ```
 /**
 * Created by fangzy on 2018/2/9 16:17
 */
public class User {
    private String name;
    //省略get、set方法
}
 ```
 