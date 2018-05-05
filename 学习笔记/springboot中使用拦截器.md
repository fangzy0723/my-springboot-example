---
title: spring boot中使用拦截器
---

> demo测试通过，使用版本如下：
> spring boot版本：1.5.10.RELEASE
> java版本：1.8
> maven版本：apache-maven-3.3.9


> 拦截器是指通过统一拦截从浏览器发往服务器的请求来完成功能的增强   
> 应用场景：解决一些共性问题，比如权限验证、乱码等


> 项目中过滤器，拦截器，aop都使用的拦截流程：请求先经过过滤器然后经过拦截器，最后被aop拦截，都通过之后再到controller。       
请求响应则是先经过aop，然后是添加@ControllerAdvice注解的类（controller层有异常抛出且aop没做异常处理时起作用），接着是经过拦截器，最后过滤器返回结果给调用方

#### spring boot中使用拦截器参考：

1, 创建一个类MyWebConfig继承WebMvcConfigurerAdapter，并重写addInterceptors方法

```
    @Configuration
    public class MyWebConfig extends WebMvcConfigurerAdapter {
        @Autowired
        MyiInterceptor myiInterceptor;
        /**
         *注册 拦截器
         */
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            //多个拦截器组成一个拦截器链
            // addPathPatterns添加拦截规则
            // excludePathPatterns排除拦截
            //拦截器myiInterceptor只拦截'/getName'的请求，不拦截'/helloWorld'
            registry.addInterceptor(myiInterceptor).addPathPatterns("/getName").excludePathPatterns("/helloWorld");
            super.addInterceptors(registry);
        }
    }
```
<!-- more -->

2, 创建一个自定义拦截器MyiInterceptor实现HandlerInterceptor接口，重写所有的方法实现自己的业务

```
    @Component
    public class MyiInterceptor implements HandlerInterceptor {
        /**
         *返回值为true请求会继续执行，false请求终止
         *@paramhttpServletRequest请求请求
         *@paramhttpServletResponse响应对象
         *@paramo被拦截的对象
         *@return
         *@throwsException
         */
        @Override
        public boolean preHandle(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Object o)throws Exception {
            System.out.println("preHandle方法执行了。。。");
            httpServletRequest.setCharacterEncoding("utf-8");//设置请求编码
            httpServletResponse.setCharacterEncoding("utf-8");//设置响应编码
            //示例  没有登录时， 转发到登录页  返回false中断请求
            //httpServletRequest.getRequestDispatcher("/login.html").forward(httpServletRequest,httpServletResponse);
            //return false；
            return true;
        }
        
        /**
         *@paramhttpServletRequest请求请求
         *@paramhttpServletResponse响应对象
         *@paramo被拦截的对象
         *@parammodelAndView可以在这个对象中设置返回的视图和试图内容
         *@throwsException
         */
        @Override
        public void postHandle(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Object o,ModelAndView modelAndView)throws Exception {
            System.out.println("postHandle方法执行了。。。。");
        }
        /**
         *请求执行完销毁数据
         *@paramhttpServletRequest请求请求
         *@paramhttpServletResponse响应对象
         *@paramo被拦截的对象
         *@parame
         *@throwsException
         */
        @Override
        public void afterCompletion(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Object o,Exception e)throws Exception {
            System.out.println("afterCompletion方法执行了。。。");
        }
    }
```

#### 多拦截器工作流程:

![image](https://upload-images.jianshu.io/upload_images/7030886-9b1fc235267b58d5.png?imageMogr2/auto-orient/)

#### 拦截器和过滤器的区别：

> 过滤器Filter依赖servlet容器，基于回调函数，作用范围大   
> 拦截器Interceptor依赖框架容器，基于反射机制，只过滤请求