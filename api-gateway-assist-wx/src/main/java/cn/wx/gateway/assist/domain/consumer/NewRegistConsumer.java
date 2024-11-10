package cn.wx.gateway.assist.domain.consumer;

import cn.wx.gateway.assist.application.GatewayApplication;
import cn.wx.gateway.assist.domain.service.RegisterGatewayService;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/9 下午3:36
 */

public class NewRegistConsumer {

    private Logger logger = LoggerFactory.getLogger(NewRegistConsumer.class);
    GatewayApplication gatewayApplication;

    public NewRegistConsumer(GatewayApplication gatewayApplication){
        this.gatewayApplication = gatewayApplication;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue01"),
            exchange = @Exchange(name = "exchange01.direct",type = ExchangeTypes.DIRECT),
            key = {"${api-gateway.gatewayId}"}
    ))
    public void queue01Listener(String msg) {
        try {
            gatewayApplication.receiveMessage(msg);
        }catch (Exception e){
            logger.info(e.getMessage(),e.getCause());
        }
    }
}
