package cn.wx.gateway.assist.config;

import cn.wx.gateway.assist.application.GatewayApplication;
import cn.wx.gateway.assist.domain.consumer.NewRegistConsumer;
import cn.wx.gateway.assist.domain.service.RegisterGatewayService;
import cn.wx.gateway.core.session.defaults.DefaultGatewaySessionFactory;
import cn.wx.gateway.core.socket.GatewaySocketServer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/6 下午9:14
 */
@Configuration
@EnableConfigurationProperties(GatewayServiceProperties.class)
@Import(RabbitMQConfig.class)
public class GatewayAutoConfig {
    private Logger logger = LoggerFactory.getLogger(GatewayAutoConfig.class);

    @Bean
    public RegisterGatewayService registerGatewayService(){return new RegisterGatewayService();}

    @Bean
    public GatewayApplication gatewayApplication(GatewayServiceProperties properties, RegisterGatewayService registerGatewayService,
                                                 cn.wx.gateway.core.session.Configuration configuration,Channel gatewaySocketServerChannel) {
        return new GatewayApplication(properties, registerGatewayService,configuration,gatewaySocketServerChannel);
    }

    @Bean
    public cn.wx.gateway.core.session.Configuration gatewayCoreConfiguration(GatewayServiceProperties properties){
        cn.wx.gateway.core.session.Configuration configuration = new cn.wx.gateway.core.session.Configuration();
        String[] split = properties.getGatewayAddress().split(":");
        configuration.setHostName(split[0].trim());
        configuration.setPort(Integer.parseInt(split[1].trim()));
        return configuration;
    }

    @Bean
    public Channel initGateway(cn.wx.gateway.core.session.Configuration configuration) throws InterruptedException, ExecutionException {
        DefaultGatewaySessionFactory gatewaySessionFactory = new DefaultGatewaySessionFactory(configuration);
        GatewaySocketServer server = new GatewaySocketServer(configuration, gatewaySessionFactory);
        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
        Channel channel = future.get();
        if (null == channel) throw new RuntimeException("api gateway core netty server start error channel is null");
        while (!channel.isActive()) {
            logger.info("api gateway core netty server gateway start Ing ...");
            Thread.sleep(500);
        }
        logger.info("api gateway core netty server gateway start Done! {}", channel.localAddress());
        return channel;
    }



    /*
    @Bean
    public RedisConnectionFactory redisConnectionFactory(GatewayServiceProperties properties, RegisterGatewayService gatewayCenterService) {
        // 1. 拉取注册中心的 Redis 配置信息
        Map<String, String> redisConfig = gatewayCenterService.queryRedisConfig(properties.getAddress());
        // 2. 构建 Redis 服务
        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration();
        standaloneConfig.setHostName(redisConfig.get("host"));
        standaloneConfig.setPort(Integer.parseInt(redisConfig.get("port")));
        // 3. 默认配置信息；一般这些配置可以被抽取出来
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(100);
        poolConfig.setMaxWaitMillis(30 * 1000);
        poolConfig.setMinIdle(20);
        poolConfig.setMaxIdle(40);
        poolConfig.setTestWhileIdle(true);
        // 4. 创建 Redis 配置
        JedisClientConfiguration clientConfig = JedisClientConfiguration.builder()
                .connectTimeout(Duration.ofSeconds(2))
                .clientName("api-gateway-assist-redis-" + properties.getGatewayId())
                .usePooling().poolConfig(poolConfig).build();
        // 5. 实例化 Redis 链接对象
        return new JedisConnectionFactory(standaloneConfig, clientConfig);
    }

    @Bean
    public RedisMessageListenerContainer container(GatewayServiceProperties properties, RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter msgAgreementListenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        //  container.addMessageListener(msgAgreementListenerAdapter, new PatternTopic("api-gateway-g4"));
        container.addMessageListener(msgAgreementListenerAdapter, new PatternTopic(properties.getGatewayId()));
        return container;
    }

    @Bean
    public MessageListenerAdapter msgAgreementListenerAdapter(GatewayApplication gatewayApplication) {
        return new MessageListenerAdapter(gatewayApplication, "receiveMessage");
    }
    */

}
