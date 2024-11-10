package cn.wx.gateway.center.domain.manage.repository;

import cn.wx.gateway.center.domain.manage.model.aggregates.ApplicationSystemRichInfo;
import cn.wx.gateway.center.domain.manage.model.vo.*;


import java.util.List;

/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/6 上午11:24
 */
public interface IConfigManageRepository {
    GatewayServerDetailVO queryGatewayServerDetail(String gatewayId, String gatewayAddress);

    boolean registerGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress, Integer status);

    boolean updateGatewayStatus(String gatewayId, String gatewayAddress, Integer available);

    List<GatewayServerVO> queryGatewayServerList();

    List<String> queryGatewayDistributionSystemIdList(String gatewayId);

    List<ApplicationSystemVO> queryApplicationSystemList(List<String> applicationSystemIds);

    List<ApplicationInterfaceVO> queryApplicationInterfaceList(String systemId);

    List<ApplicationInterfaceMethodVO> queryApplicationInterfaceMethodList(String systemId, String interfaceId);

    String queryGatewayDistribution(String systemId);

    List<GatewayServerDetailVO> queryGatewayServerDetailList();
}
