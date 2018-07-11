package cn.com.example.fanout.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fangzy on 2018/7/11 20:13
 */
@Configuration
public class ExchangeFanoutConfig {
    /**
     * 声明三个队列
     * @return
     */
    @Bean
    public Queue queue1(){
        return new Queue("queue1");
    }

    @Bean
    public Queue queue2(){
        return new Queue("queue2");
    }

    @Bean
    public Queue queue3(){
        return new Queue("queue3");
    }

    /**
     * 声明一个fanout类型的交换机
     * fanout类型的交换机：会把发送到这个交换机的消息发送到跟这个交换机绑定的所有队列上
     *
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 把队列绑定到交换机上
     * @param queue1
     * @param fanoutExchange
     * @return
     */
    @Bean
    Binding binding1(Queue queue1,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue1).to(fanoutExchange);
    }
    @Bean
    Binding binding2(Queue queue2,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue2).to(fanoutExchange);
    }
    @Bean
    Binding binding3(Queue queue3,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue3).to(fanoutExchange);
    }
}
