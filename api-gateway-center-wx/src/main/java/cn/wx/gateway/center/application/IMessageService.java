package cn.wx.gateway.center.application;

import java.util.Map;

/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/9 上午10:54
 */
public interface IMessageService {

    void pushMessage(String gatewayId, String systemId);
    Map<String, String> queryRabbitMQConfig();
}
