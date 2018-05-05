package cn.com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fangzy on 2018/4/15 17:45
 */
@Service("rBACService")
@Slf4j
public class RBACServiceImpl implements RBACService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        boolean hasPermission = false;

        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails){
            //获取到登陆的用户名
            String username = ((UserDetails)principal).getUsername();

            //获取用户有权限的URL
            Set<String> urls = new HashSet<>();
            urls.add("/findAll");
            for (String url:urls){
                log.info("请求的url："+request.getRequestURI());
                if(antPathMatcher.match(url,request.getRequestURI())){
                    //说明请求的URL是该用户有权限访问的
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
