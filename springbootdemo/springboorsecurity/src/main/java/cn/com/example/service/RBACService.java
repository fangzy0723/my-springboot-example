package cn.com.example.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fangzy on 2018/4/15 17:44
 */
public interface RBACService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
