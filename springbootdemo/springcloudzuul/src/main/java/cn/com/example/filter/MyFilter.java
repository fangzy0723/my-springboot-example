package cn.com.example.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by fangzy on 2018/4/22 17:39
 */
@Component
public class MyFilter extends ZuulFilter {
    Logger logger = LoggerFactory.getLogger(MyFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * zuul网关拦截参数，请求中没有token参数则不能请求到对应的方法
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        logger.info("请求方法：{}；请求url：{}",httpServletRequest.getMethod(),httpServletRequest.getRequestURL());

        Object token = httpServletRequest.getParameter("token");
        if(token != null){
            return null;
        }

        logger.info("token is empty");
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(401);
        try {
            requestContext.getResponse().getWriter().write("token is empty");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
