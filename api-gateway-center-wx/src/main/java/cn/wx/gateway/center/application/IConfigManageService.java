package cn.wx.gateway.center.application;

import cn.wx.gateway.center.domain.manage.model.aggregates.ApplicationSystemRichInfo;
import cn.wx.gateway.center.domain.manage.model.vo.GatewayServerDetailVO;
import cn.wx.gateway.center.domain.manage.model.vo.GatewayServerVO;

import java.util.List;

/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/6 上午11:21
 */
public interface IConfigManageService {
    boolean registerGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress);
    List<GatewayServerVO> queryGatewayServerList();

    ApplicationSystemRichInfo queryApplicationSystemRichInfo(String gatewayId);

    String queryGatewayDistribution(String systemId);

    ApplicationSystemRichInfo queryApplicationSystemRichInfo(String gatewayId, String systemId);

    List<GatewayServerDetailVO> queryGatewayServerDetailList();
}
