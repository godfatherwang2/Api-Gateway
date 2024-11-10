package cn.wx.gateway.sdk.application;

import cn.wx.gateway.sdk.GatewayException;
import cn.wx.gateway.sdk.annotation.ApiProducerClazz;
import cn.wx.gateway.sdk.annotation.ApiProducerMethod;
import cn.wx.gateway.sdk.config.GatewaySDKServiceProperties;
import cn.wx.gateway.sdk.domian.service.GatewayCenterService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class GatewaySDKApplication implements BeanPostProcessor {

    private Logger logger = LoggerFactory.getLogger(GatewaySDKApplication.class);

    private GatewaySDKServiceProperties properties;

    private GatewayCenterService gatewayCenterService;

    public GatewaySDKApplication(GatewaySDKServiceProperties properties,GatewayCenterService gatewayCenterService) {
        this.properties = properties;
        this.gatewayCenterService = gatewayCenterService;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        ApiProducerClazz apiProducerClazz = bean.getClass().getAnnotation(ApiProducerClazz.class);
        if (null == apiProducerClazz) return bean;
        gatewayCenterService.doRegisterApplication(properties.getAddress(), properties.getSystemId(), properties.getSystemName(), "RPC", properties.getSystemRegistry());
        // 1. 系统信息
        logger.info("\n应用注册：系统信息 \nsystemId: {} \nsystemName: {} \nsystemType: {} \nsystemRegistry: {}", properties.getSystemId(), properties.getSystemName(), "RPC", properties.getSystemRegistry());

        // 2. 接口信息
        Class<?>[] interfaces = bean.getClass().getInterfaces();
        if (interfaces.length != 1) {
            throw new GatewayException(bean.getClass().getName() + "interfaces not one this is " + JSON.toJSONString(interfaces));
        }
        String interfaceId = interfaces[0].getName();
        logger.info("\n应用注册：接口信息 \nsystemId: {} \ninterfaceId: {} \ninterfaceName: {} \ninterfaceVersion: {}", properties.getSystemId(), bean.getClass().getName(), apiProducerClazz.interfaceName(), apiProducerClazz.interfaceVersion());
        gatewayCenterService.doRegisterApplicationInterface(properties.getAddress(), properties.getSystemId(),
                interfaceId,apiProducerClazz.interfaceName(),apiProducerClazz.interfaceVersion());

        // 3. 方法信息
        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            ApiProducerMethod apiProducerMethod = method.getAnnotation(ApiProducerMethod.class);
            if(apiProducerMethod==null) {
                continue;
            }
            Class<?>[] parameterTypes = method.getParameterTypes();
            StringBuilder parameters = new StringBuilder();
            for(Class<?> clazz : parameterTypes){
                parameters.append(clazz.getName()).append(",");
            }

            String parameterType = parameters.toString().substring(0,parameters.toString().lastIndexOf(","));
            logger.info("\n应用注册：方法信息 \nsystemId: {} \ninterfaceId: {} \nmethodId: {} \nmethodName: {} \nparameterType: {} \nuri: {} \nhttpCommandType: {} \nauth: {}",
                    properties.getSystemId(),
                    bean.getClass().getName(),
                    method.getName(),
                    apiProducerMethod.methodName(),
                    parameterType,
                    apiProducerMethod.uri(),
                    apiProducerMethod.httpCommandType(),
                    apiProducerMethod.auth());
            gatewayCenterService.doRegisterApplicationInterfaceMethod(properties.getAddress(),
                    properties.getSystemId(),
                    interfaceId,
                    method.getName(),
                    apiProducerMethod.methodName(),
                    parameterType,
                    apiProducerMethod.uri(),
                    apiProducerMethod.httpCommandType(),
                    apiProducerMethod.auth());
        }
        // 注册完成，执行事件通知
        gatewayCenterService.doRegisterEvent(properties.getAddress(),properties.getSystemId());
        return bean;
    }
}




