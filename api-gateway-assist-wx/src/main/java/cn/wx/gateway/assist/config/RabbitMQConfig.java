package cn.wx.gateway.assist.config;

import cn.wx.gateway.assist.application.GatewayApplication;
import cn.wx.gateway.assist.domain.consumer.NewRegistConsumer;
import cn.wx.gateway.assist.domain.service.RegisterGatewayService;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;


import java.util.Map;

/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/9 下午3:33
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public ConnectionFactory rabbitMQConnectionFactory(GatewayServiceProperties properties,RegisterGatewayService gatewayCenterService){
        Map<String, String> rabbitMQConfig = gatewayCenterService.queryRabbitMQConfig(properties.getAddress());
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitMQConfig.get("host"));
        connectionFactory.setPort(Integer.parseInt(rabbitMQConfig.get("port")));
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");
        return connectionFactory;
    }

    @Bean
    public NewRegistConsumer newRegistConsumer(GatewayApplication gatewayApplication){
        return new NewRegistConsumer(gatewayApplication);
    }

    /*
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("exchange01.direct");
    }

    @Bean
    public Queue queue() {
        return new Queue("direct.queue01");
    }
    */


}
