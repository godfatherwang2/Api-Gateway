package cn.wx.gateway.center.infrastructure.repository;

import cn.hutool.core.bean.BeanUtil;
import cn.wx.gateway.center.domain.manage.model.vo.*;
import cn.wx.gateway.center.domain.manage.repository.IConfigManageRepository;
import cn.wx.gateway.center.infrastructure.dao.*;
import cn.wx.gateway.center.infrastructure.po.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/6 上午11:30
 */
@Repository
public class ConfigManageRepository implements IConfigManageRepository {

    @Resource
    IGatewayServerDao gatewayServerDao;
    @Resource
    IGatewayServerDetailDao gatewayServerDetailDao;
    @Resource
    IGatewayDistributionDao gatewayDistributionDao;
    @Resource
    IApplicationSystemDao applicationSystemDao;

    @Resource
    IApplicationInterfaceDao applicationInterfaceDao;

    @Resource
    IApplicationInterfaceMethodDao applicationInterfaceMethodDao;

    @Override
    public GatewayServerDetailVO queryGatewayServerDetail(String gatewayId, String gatewayAddress) {
        GatewayServerDetail gatewayServerDetail = gatewayServerDetailDao.selectOne(new LambdaQueryWrapper<GatewayServerDetail>().eq(GatewayServerDetail::getGatewayId,gatewayId)
                .eq(GatewayServerDetail::getGatewayAddress,gatewayAddress));
        if (null == gatewayServerDetail) return null;
        return BeanUtil.copyProperties(gatewayServerDetail,GatewayServerDetailVO.class);
    }

    @Override
    public boolean registerGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress, Integer status) {
        GatewayServerDetail gatewayServerDetail = new GatewayServerDetail();
        gatewayServerDetail.setGroupId(groupId);
        gatewayServerDetail.setGatewayId(gatewayId);
        gatewayServerDetail.setGatewayName(gatewayName);
        gatewayServerDetail.setGatewayAddress(gatewayAddress);
        gatewayServerDetail.setStatus(status);
        gatewayServerDetailDao.insert(gatewayServerDetail);
        return true;
    }

    @Override
    public boolean updateGatewayStatus(String gatewayId, String gatewayAddress, Integer available) {
        GatewayServerDetail gatewayServerDetail = new GatewayServerDetail();
        gatewayServerDetail.setGatewayId(gatewayId);
        gatewayServerDetail.setGatewayAddress(gatewayAddress);
        gatewayServerDetail.setStatus(available);
        gatewayServerDetailDao.update(null,new LambdaUpdateWrapper<GatewayServerDetail>().set(GatewayServerDetail::getStatus,available)
                .eq(GatewayServerDetail::getGatewayId,gatewayId)
                .eq(GatewayServerDetail::getGatewayAddress,gatewayAddress));
        return true;

    }

    @Override
    public List<GatewayServerVO> queryGatewayServerList() {
        List<GatewayServer> gatewayServers = gatewayServerDao.selectList(null);
        List<GatewayServerVO> gatewayServerVOList = new ArrayList<>(gatewayServers.size());
        for (GatewayServer gatewayServer : gatewayServers) {
            // 可以按照 IDEA 插件 vo2dto 方便转换
            GatewayServerVO gatewayServerVO = new GatewayServerVO();
            gatewayServerVO.setGroupId(gatewayServer.getGroupId());
            gatewayServerVO.setGroupName(gatewayServer.getGroupName());
            gatewayServerVOList.add(gatewayServerVO);
        }
        return gatewayServerVOList;
    }


    @Override
    public List<String> queryGatewayDistributionSystemIdList(String gatewayId) {
        List<String> ids = gatewayDistributionDao.selectList(new LambdaQueryWrapper<GatewayDistribution>().select(GatewayDistribution::getSystemId).
                eq(GatewayDistribution::getGatewayId,gatewayId)).stream().map(GatewayDistribution::getSystemId).collect(Collectors.toList());
        return ids;
    }

    @Override
    public List<ApplicationSystemVO> queryApplicationSystemList(List<String> applicationSystemIds) {
        List<ApplicationSystem> applicationSystems = applicationSystemDao.selectList(new LambdaQueryWrapper<ApplicationSystem>().
                in(ApplicationSystem::getSystemId,applicationSystemIds));


        List<ApplicationSystemVO> applicationSystemVOS = applicationSystems.stream().map(
                applicationSystem -> BeanUtil.copyProperties(applicationSystem,ApplicationSystemVO.class)
        ).collect(Collectors.toList());
        return applicationSystemVOS;
    }

    @Override
    public List<ApplicationInterfaceVO> queryApplicationInterfaceList(String systemId) {
        List<ApplicationInterface> applicationInterfaces = applicationInterfaceDao.queryApplicationInterfaceList(systemId);
        List<ApplicationInterfaceVO> applicationInterfaceVOList = new ArrayList<>(applicationInterfaces.size());
        for (ApplicationInterface applicationInterface : applicationInterfaces) {
            ApplicationInterfaceVO applicationInterfaceVO = new ApplicationInterfaceVO();
            applicationInterfaceVO.setSystemId(applicationInterface.getSystemId());
            applicationInterfaceVO.setInterfaceId(applicationInterface.getInterfaceId());
            applicationInterfaceVO.setInterfaceName(applicationInterface.getInterfaceName());
            applicationInterfaceVO.setInterfaceVersion(applicationInterface.getInterfaceVersion());
            applicationInterfaceVOList.add(applicationInterfaceVO);
        }
        return applicationInterfaceVOList;
    }

    @Override
    public List<ApplicationInterfaceMethodVO> queryApplicationInterfaceMethodList(String systemId, String interfaceId) {
        ApplicationInterfaceMethod req = new ApplicationInterfaceMethod();
        req.setSystemId(systemId);
        req.setInterfaceId(interfaceId);
        List<ApplicationInterfaceMethod> applicationInterfaceMethods = applicationInterfaceMethodDao.queryApplicationInterfaceMethodList(req);
        List<ApplicationInterfaceMethodVO> applicationInterfaceMethodVOList = new ArrayList<>(applicationInterfaceMethods.size());
        for (ApplicationInterfaceMethod applicationInterfaceMethod : applicationInterfaceMethods) {
            ApplicationInterfaceMethodVO applicationInterfaceMethodVO = new ApplicationInterfaceMethodVO();
            applicationInterfaceMethodVO.setSystemId(applicationInterfaceMethod.getSystemId());
            applicationInterfaceMethodVO.setInterfaceId(applicationInterfaceMethod.getInterfaceId());
            applicationInterfaceMethodVO.setMethodId(applicationInterfaceMethod.getMethodId());
            applicationInterfaceMethodVO.setMethodName(applicationInterfaceMethod.getMethodName());
            applicationInterfaceMethodVO.setParameterType(applicationInterfaceMethod.getParameterType());
            applicationInterfaceMethodVO.setUri(applicationInterfaceMethod.getUri());
            applicationInterfaceMethodVO.setHttpCommandType(applicationInterfaceMethod.getHttpCommandType());
            applicationInterfaceMethodVO.setAuth(applicationInterfaceMethod.getAuth());
            applicationInterfaceMethodVOList.add(applicationInterfaceMethodVO);
        }
        return applicationInterfaceMethodVOList;
    }

    @Override
    public String queryGatewayDistribution(String systemId) {
        GatewayDistribution gatewayDistribution = gatewayDistributionDao.selectOne(new LambdaQueryWrapper<GatewayDistribution>().eq(GatewayDistribution::getSystemId,systemId));
        if (gatewayDistribution == null) {
            return null;
        }
        return gatewayDistribution.getGatewayId();
    }

    @Override
    public List<GatewayServerDetailVO> queryGatewayServerDetailList() {
        List<GatewayServerDetail> gatewayServerDetails = gatewayServerDetailDao.selectList(null);
        List<GatewayServerDetailVO> gatewayServerDetailVOS = gatewayServerDetails.stream().map(a->BeanUtil.copyProperties(a, GatewayServerDetailVO.class)).collect(Collectors.toList());
        return gatewayServerDetailVOS;
    }
}
