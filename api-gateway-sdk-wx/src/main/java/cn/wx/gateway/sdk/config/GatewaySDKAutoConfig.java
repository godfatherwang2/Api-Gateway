package cn.wx.gateway.sdk.config;

import cn.wx.gateway.sdk.application.GatewaySDKApplication;
import cn.wx.gateway.sdk.config.GatewaySDKServiceProperties;
import cn.wx.gateway.sdk.domian.service.GatewayCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 小傅哥，微信：fustack
 * @description 网关SDK配置服务
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Configuration
@EnableConfigurationProperties(GatewaySDKServiceProperties.class)
public class GatewaySDKAutoConfig {

    private Logger logger = LoggerFactory.getLogger(GatewaySDKAutoConfig.class);

    @Bean
    public GatewayCenterService gatewayCenterService(){
        return new GatewayCenterService();
    }

    @Bean
    public GatewaySDKApplication gatewaySDKApplication(GatewaySDKServiceProperties properties,GatewayCenterService gatewayCenterService) {
        return new GatewaySDKApplication(properties,gatewayCenterService);
    }

}
