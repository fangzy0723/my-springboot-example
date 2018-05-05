package cn.com.example.config;

import cn.com.example.authorize.AuthorizeConfigManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * Created by fangzy on 2018/4/15 10:34
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailureHandler;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        //会在数据库创建一张表，保存token、用户名等信息到新创建的表中
        jdbcTokenRepository.setDataSource(dataSource);
        //true表示会创建一张记录用户token的表，已经存在会启动报错，可以在数据库客户端执行这段sql脚本自行创建表
//        create table persistent_logins (username varchar(64) not null, series varchar(64) primary key, token varchar(64) not null, last_used timestamp not null)
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }


    /**
     //antMatchers 里面的请求不进行授权认证
     //.antMatchers("/authentication/require","/loginPage.html","/toFail.html","/toSuccess.html").permitAll()
     //findAll 且是post 请求需要admin权限
     //.antMatchers(HttpMethod.POST,"/findAll").hasRole("ADMIN")
     ///findAll 且是post 请求需要admin权限且IP是指定的ip才行
     //.antMatchers(HttpMethod.POST,"/findAll").access("hasRole('ADMIN') and hasIpAddress('XXXX')")
     //其他的请求都需要进行授权认证
     //.anyRequest().authenticated()
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        authorizeConfigManager.config(http.authorizeRequests());
        http
//          这部分提到了AuthorizeConfigManager接口中
//         .authorizeRequests()
//                //antMatchers 里面的请求不进行授权认证
//                .antMatchers("/authentication/require","/loginPage.html","/toFail.html","/toSuccess.html").permitAll()
//                .anyRequest().authenticated()
//                .and()
          .formLogin()
                ////当请求需要认证时，跳转到这个请求地址
                .loginPage("/authentication/require")
                //登陆请求
                .loginProcessingUrl("/authentication/login")
                //登陆成功处理的方法
                .successHandler(customAuthenticationSuccessHandler)
                //登陆失败处理的方法
                .failureHandler(customAuthenticationFailureHandler)
                .and()
          //记住我功能配置
          .rememberMe()
                //token仓库
                .tokenRepository(persistentTokenRepository())
                //保存时间，单位秒
                .tokenValiditySeconds(60*60*24*7)
                //查询用户登录信息
                .userDetailsService(userDetailsService)
                .and()
          .csrf().disable();
    }
}
