package cn.com.example.controller;

import cn.com.example.domain.ResultBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by fangzy on 2018/1/26 12:57
 */
@RestController
public class SecurityContoller {
    private Logger logger = LoggerFactory.getLogger(SecurityContoller.class);

    /**
     * spring缓存请求信息的对象
     */
    private RequestCache requestCache = new HttpSessionRequestCache();
    /**
     * spring 提供的处理转发的工具
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    /**
     * 拦截到需要认证的请求，跳转到这个方法
     * 该请求是不是以.html结尾的，是以.html结尾的则跳转到登陆页面，否则返回需要认证的信息
     * 不是登陆的请求返回没有认证的状态码
     * @return
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultBean requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //获取缓存的请求信息
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest != null){
            String redirectUrlPath = savedRequest.getRedirectUrl();
            logger.info("引发跳转到这个方法的请求地址：{}",redirectUrlPath);
            //判断该请求是不是以.html结尾的，是以.html结尾的则跳转到登陆页面，否则返回需要认证的信息
            if(StringUtils.endsWithIgnoreCase(redirectUrlPath,".html")){
                //转发到登陆页面
                logger.info("securityProperties.getBorwser().getLoginPage():/loginPage");
                redirectStrategy.sendRedirect(request,response,"/loginPage.html?asd=nologin");
            }else{
                redirectStrategy.sendRedirect(request,response,"/loginPage.html?asd=123");
            }
        }
        return new ResultBean(HttpStatus.UNAUTHORIZED.value(),"认证失败，没有权限访问",null);
    }
}
