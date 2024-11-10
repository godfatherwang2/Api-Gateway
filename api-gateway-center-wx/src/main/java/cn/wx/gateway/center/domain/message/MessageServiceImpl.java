package cn.wx.gateway.center.domain.message;

import cn.wx.gateway.center.application.IMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/9 上午10:54
 */
@Service
public class MessageServiceImpl implements IMessageService {

    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private Integer port;

    @Resource
    Publisher publisher;

    @Override
    public void pushMessage(String gatewayId, String systemId) {
        publisher.PushMessage(gatewayId,systemId);
    }

    @Override
    public Map<String, String> queryRabbitMQConfig() {
        Map<String,String> rstMap = new HashMap<>();
        rstMap.put("host",this.host);
        rstMap.put("port",String.valueOf(this.port));
        return rstMap;
    }
}
