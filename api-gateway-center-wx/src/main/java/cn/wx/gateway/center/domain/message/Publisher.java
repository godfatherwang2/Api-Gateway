package cn.wx.gateway.center.domain.message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/9 上午10:42
 */
@Service
public class Publisher {
    private final RabbitTemplate rabbitTemplate;

    public Publisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void PushMessage(String gatewayId, String systemId){
        rabbitTemplate.convertAndSend("exchange01.direct",gatewayId,systemId);
    }

}
