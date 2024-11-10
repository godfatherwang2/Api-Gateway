package cn.wx.gateway.center.domain.manage.service;

import cn.hutool.core.util.StrUtil;
import cn.wx.gateway.center.application.IConfigManageService;
import cn.wx.gateway.center.domain.manage.model.aggregates.ApplicationSystemRichInfo;
import cn.wx.gateway.center.domain.manage.model.vo.GatewayServerDetailVO;
import cn.wx.gateway.center.domain.manage.model.vo.GatewayServerVO;
import cn.wx.gateway.center.domain.manage.repository.IConfigManageRepository;
import cn.wx.gateway.center.domain.manage.model.vo.ApplicationInterfaceMethodVO;
import cn.wx.gateway.center.domain.manage.model.vo.ApplicationInterfaceVO;
import cn.wx.gateway.center.domain.manage.model.vo.ApplicationSystemVO;
import cn.wx.gateway.center.infrastructure.common.Constants;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/6 上午11:22
 */
@Service
public class ConfigManageService implements IConfigManageService {

    @Resource
    IConfigManageRepository configManageRepository;


    @Override
    public boolean registerGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress) {
        GatewayServerDetailVO gatewayServerDetailVO = configManageRepository.queryGatewayServerDetail(gatewayId, gatewayAddress);
        if (null == gatewayServerDetailVO) {
            try {
                return configManageRepository.registerGatewayServerNode(groupId, gatewayId, gatewayName, gatewayAddress, Constants.GatewayStatus.Available);
            } catch (DuplicateKeyException e) {
                return configManageRepository.updateGatewayStatus(gatewayId, gatewayAddress, Constants.GatewayStatus.Available);
            }
        } else {
            return configManageRepository.updateGatewayStatus(gatewayId, gatewayAddress, Constants.GatewayStatus.Available);
        }
    }

    @Override
    public List<GatewayServerVO> queryGatewayServerList() {
        return configManageRepository.queryGatewayServerList();
    }

    @Override
    public ApplicationSystemRichInfo queryApplicationSystemRichInfo(String gatewayId) {
        List<String> applicationSystemIds = configManageRepository.queryGatewayDistributionSystemIdList(gatewayId);
        List<ApplicationSystemVO> applicationSystemVos = configManageRepository.queryApplicationSystemList(applicationSystemIds);
        // 3. 查询系统下的接口信息
        // 思考：这里的查询是一个不断地循环的查询，你是否有办法优化下，减少查询次数。
        for (ApplicationSystemVO applicationSystemVO : applicationSystemVos){
            List<ApplicationInterfaceVO> applicationInterfaceVOList = configManageRepository.queryApplicationInterfaceList(applicationSystemVO.getSystemId());
            for (ApplicationInterfaceVO applicationInterfaceVO : applicationInterfaceVOList) {
                List<ApplicationInterfaceMethodVO> applicationInterfaceMethodVOList = configManageRepository.queryApplicationInterfaceMethodList(applicationSystemVO.getSystemId(), applicationInterfaceVO.getInterfaceId());
                applicationInterfaceVO.setMethodList(applicationInterfaceMethodVOList);
            }
            applicationSystemVO.setInterfaceList(applicationInterfaceVOList);
        }
        return new ApplicationSystemRichInfo(gatewayId, applicationSystemVos);
    }

    @Override
    public String queryGatewayDistribution(String systemId) {
        return configManageRepository.queryGatewayDistribution(systemId);
    }

    @Override
    public ApplicationSystemRichInfo queryApplicationSystemRichInfo(String gatewayId, String systemId) {
        if(StrUtil.isBlank(systemId)){
            return queryApplicationSystemRichInfo(gatewayId);
        }
        else {
            // 1. 查询出网关ID对应的关联系统ID集合。也就是一个网关ID会被分配一些系统RPC服务注册进来，需要把这些服务查询出来。
            List<String> systemIdList = new ArrayList<>();
            if (null == systemId){
                systemIdList = configManageRepository.queryGatewayDistributionSystemIdList(gatewayId);
            } else {
                systemIdList.add(systemId);
            }
            List<ApplicationSystemVO> applicationSystemVOList = configManageRepository.queryApplicationSystemList(systemIdList);
            for (ApplicationSystemVO applicationSystemVO : applicationSystemVOList) {
                List<ApplicationInterfaceVO> applicationInterfaceVOList = configManageRepository.queryApplicationInterfaceList(applicationSystemVO.getSystemId());
                for (ApplicationInterfaceVO applicationInterfaceVO : applicationInterfaceVOList) {
                    List<ApplicationInterfaceMethodVO> applicationInterfaceMethodVOList = configManageRepository.queryApplicationInterfaceMethodList(applicationSystemVO.getSystemId(), applicationInterfaceVO.getInterfaceId());
                    applicationInterfaceVO.setMethodList(applicationInterfaceMethodVOList);
                }
                applicationSystemVO.setInterfaceList(applicationInterfaceVOList);
            }
            return new ApplicationSystemRichInfo(gatewayId, applicationSystemVOList);
        }
    }

    @Override
    public List<GatewayServerDetailVO> queryGatewayServerDetailList() {
        return configManageRepository.queryGatewayServerDetailList();
    }
}
