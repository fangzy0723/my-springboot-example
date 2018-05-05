package cn.com.example.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by fangzy on 2018/4/15 16:54
 */
@Component
public class AuthorizeConfigManagerImpl implements AuthorizeConfigManager {

    @Autowired
    private List<AuthorizeConfigProvider> authorizeConfigProviders;

    /**
     *         //其余的请求都需要验证
     //        config.anyRequest().authenticated();
     * @param config
     */
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        //先加载所有不需要验证的请求
        for(AuthorizeConfigProvider authorizeConfigProvider:authorizeConfigProviders){
            authorizeConfigProvider.config(config);
        }


    }
}
