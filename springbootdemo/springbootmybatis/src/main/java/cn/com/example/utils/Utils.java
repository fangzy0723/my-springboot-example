package cn.com.example.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fangzy on 2018/3/21 9:26
 */
public class Utils {

    /**
     * 服务端判断请求是不是ajax请求
     * Ajax 请求比传统请求的协议头（header）多了个"X-Requested-With  XMLHttpRequest"
     * 可以利用它,request.getHeader("X-Requested-With");
     * 为 null,则为传统同步请求,为 XMLHttpRequest,则为 Ajax 异步请求。
     * @param request
     * @return 是返回true，不是返回false
     */
    public static Boolean isAjax(HttpServletRequest request){
        return (request.getHeader("X-Requested-With") != null &&
            "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }
}
