package cn.com.example.oneToMany.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fangzy on 2018/7/11 19:24
 */
@Configuration
public class RabbitMQOneToManyConfig {

    /**
     * 测试多个消费者消费一个队列中的消息
     * @return
     */
    @Bean
    public Queue one2many(){
        return new Queue("one2many");
    }

}
