package cn.com.example.direct.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fangzy on 2018/7/11 20:34
 */
@Configuration
public class ExchangeDirectConfig {

    @Bean
    public Queue queueMsgs(){
        return new Queue("queueMsgs");
    }


    /**
     * direct类型的交换机是完全匹配
     * @return
     */
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    @Bean
    Binding directExchangeBinding(Queue queueMsgs,DirectExchange directExchange){
        return BindingBuilder.bind(queueMsgs).to(directExchange).with("topic.asd");
    }
}
