---
title: spring boot 验证及自定义验证
---

#### spring boot 验证及自定义验证参考示例

> 本参考实例只是主要代码，用到的service相关代码根据自己的逻辑处理，在这省略了

- #### controller

```
    /**
     * 添加用户
     * @Valid 注解是为了验证用户参数
     * @RequestBody 注解是为了把接口调用传过来的json参数内容封装到user对象中
     * BindingResult 对象中存放参数验证不通过的信息包含字段和错误信息
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping("/addUsers")
    public User addUsers(@Valid @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuffer sb = new StringBuffer();
            bindingResult.getAllErrors().stream().forEach(error -> {
                //error的类型是FieldError，可以从FieldError中获取到错误的字段和错误信息
                //FieldError fieldError = (FieldError)error;
                //System.out.println(fieldError.getField()+"   "+fieldError.getDefaultMessage());
                sb.append(error.getDefaultMessage()+"；");
                System.out.println(error.getDefaultMessage());
            });
            //验证不通过抛出自定义异常
            throw new CustomException(10,sb.toString());
        }
        System.out.println(user.toString());
        return user;
    }
```

<!-- more -->

- #### domain

```
public class User implements Serializable {

    private Long id;
    @Min(value = 18,message = "年龄必须大于18岁")
    @Max(value = 100,message = "年龄不能大于100岁")
    private Integer age;
    @NotBlank(message = "用户名不能为空")
    @CheckUserName(message = "用户名已被使用")
    private String name;
    @NotBlank(message = "密码不能为空")
    private String password;
    @Past(message = "出生日期不能使将来时")
    private Date birthday;

    //省略get、set方法
}
```

- #### 自定义验证注解

```
/**
 * Created by fangzy on 2018/1/24 15:50
 */
//指定该注解可以加到的位置
@Target({ElementType.METHOD,ElementType.FIELD})
//执行的时机
@Retention(RetentionPolicy.RUNTIME)
//指定这个自定义注解的检验类
@Constraint(validatedBy = CheckUserNameValidator.class)
public @interface CheckUserName {
    String message() default "用户名验证不通过";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
```

- #### 自定义验证注解处理

```
/**
 * CheckUserName:自定义注解名
 * Created by fangzy on 2018/1/24 15:56
 */
public class CheckUserNameValidator implements ConstraintValidator<CheckUserName, Object> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(CheckUserName constraintAnnotation) {
        System.out.println("验证用户名的自定义Validator初始化...");
    }

    /**
     * 验证用户名是否被使用，根据传过来的用户名查询数据库，能查到说明被使用，查不到没被使用
     * @param value
     * @param context
     * @return true:用户名没有被使用表示验证通过  false：用户名已被使用表示验证不通过
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println("被验证的用户名:"+value);
        User user = userService.findByName(value.toString());
        if(user==null||StringUtils.isEmpty(user.getName())){
            return true;
        }
        return false;
    }
}
```