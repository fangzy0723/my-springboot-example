package cn.com.example.topic.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fangzy on 2018/7/11 20:34
 */
@Configuration
public class ExchangeTopicConfig {

    @Bean
    public Queue queueMsga1(){
        return new Queue("queueMsga1");
    }

    @Bean
    public Queue queueMsga2(){
        return new Queue("queueMsga2");
    }

    /**
     * topic类型的交换机是模糊匹配
     * @return
     */
    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    /**
     * *:匹配1个单词
     * @param queueMsga1
     * @param topicExchange
     * @return
     */
    @Bean
    Binding topicExchangeBinding1(Queue queueMsga1,TopicExchange topicExchange){
        return BindingBuilder.bind(queueMsga1).to(topicExchange).with("topic.*");
    }

    /**
     * #:匹配0个或者多个单词
     * @param queueMsga2
     * @param topicExchange
     * @return
     */
    @Bean
    Binding topicExchangeBinding2(Queue queueMsga2,TopicExchange topicExchange){
        return BindingBuilder.bind(queueMsga2).to(topicExchange).with("topic.#");
    }



}
