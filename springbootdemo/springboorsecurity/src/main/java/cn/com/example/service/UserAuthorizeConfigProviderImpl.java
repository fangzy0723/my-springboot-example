package cn.com.example.service;

import cn.com.example.authorize.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * Created by fangzy on 2018/4/15 17:10
 * Order  值越小越先被加载
 */
@Component
@Order(Integer.MAX_VALUE)
public class UserAuthorizeConfigProviderImpl implements AuthorizeConfigProvider {

    /**
     * //        config.antMatchers(HttpMethod.POST,"/findAll").hasRole("ADMIN")
     //                .antMatchers("/findUserById/*").hasRole("AAA");
     * @param config
     */
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        //所有其他的请求都去rBACService的hasPermission方法验证
        //需要放到最后执行
        config.anyRequest().access("@rBACService.hasPermission(request,authentication)");


    }
}
