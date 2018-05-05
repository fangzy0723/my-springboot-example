package cn.com.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * 当服务调用失败，启用断路器到OrderServiceImpl中的getMember方法
 * 服务请求过来先走接口网关zuul-service，在接口网关中配置请求以/member-service/**
 * 开头的代理请求到member-service服务中的/member/getMemberService
 * token=123：参数是因为在接口网关过滤器中验证没有token参数的话会校验过不去
 * Created by fangzy on 2018/4/22 18:19
 */

@FeignClient(value = "zuul-service",fallback = OrderServiceImpl.class)
public interface OrderService {

    @GetMapping("/member-service/member/getMemberService?token=123")
    public Map<String,String> getMember();
}
