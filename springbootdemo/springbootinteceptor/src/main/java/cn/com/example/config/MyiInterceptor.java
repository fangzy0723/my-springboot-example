package cn.com.example.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fangzy on 2018/2/28 9:56
 */
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
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
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
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
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
