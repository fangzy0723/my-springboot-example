package cn.com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by fangzy on 2018/2/28 9:58
 */
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
