package cn.wx.gateway.center.infrastructure.repository;

import cn.wx.gateway.center.domain.register.repository.IRegisterManageRepository;
import cn.wx.gateway.center.domain.register.vo.ApplicationInterfaceMethodVO;
import cn.wx.gateway.center.domain.register.vo.ApplicationInterfaceVO;
import cn.wx.gateway.center.domain.register.vo.ApplicationSystemVO;
import cn.wx.gateway.center.infrastructure.dao.IApplicationInterfaceDao;
import cn.wx.gateway.center.infrastructure.dao.IApplicationInterfaceMethodDao;
import cn.wx.gateway.center.infrastructure.dao.IApplicationSystemDao;
import cn.wx.gateway.center.infrastructure.po.ApplicationInterface;
import cn.wx.gateway.center.infrastructure.po.ApplicationInterfaceMethod;
import cn.wx.gateway.center.infrastructure.po.ApplicationSystem;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/6 下午9:32
 */
@Repository
public class RegisterManageRepository implements IRegisterManageRepository {
    @Resource
    private IApplicationSystemDao applicationSystemDao;
    @Resource
    private IApplicationInterfaceDao applicationInterfaceDao;
    @Resource
    private IApplicationInterfaceMethodDao applicationInterfaceMethodDao;

    @Override
    public void registerApplication(ApplicationSystemVO applicationSystemVO) {
        ApplicationSystem applicationSystem = new ApplicationSystem();
        applicationSystem.setSystemId(applicationSystemVO.getSystemId());
        applicationSystem.setSystemName(applicationSystemVO.getSystemName());
        applicationSystem.setSystemType(applicationSystemVO.getSystemType());
        applicationSystem.setSystemRegistry(applicationSystemVO.getSystemRegistry());
        applicationSystemDao.insert(applicationSystem);
    }

    @Override
    public void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO) {
        ApplicationInterface applicationInterface = new ApplicationInterface();
        applicationInterface.setSystemId(applicationInterfaceVO.getSystemId());
        applicationInterface.setInterfaceId(applicationInterfaceVO.getInterfaceId());
        applicationInterface.setInterfaceName(applicationInterfaceVO.getInterfaceName());
        applicationInterface.setInterfaceVersion(applicationInterfaceVO.getInterfaceVersion());
        applicationInterfaceDao.insert(applicationInterface);
    }

    @Override
    public void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO) {
        ApplicationInterfaceMethod applicationInterfaceMethod = new ApplicationInterfaceMethod();
        applicationInterfaceMethod.setSystemId(applicationInterfaceMethodVO.getSystemId());
        applicationInterfaceMethod.setInterfaceId(applicationInterfaceMethodVO.getInterfaceId());
        applicationInterfaceMethod.setMethodId(applicationInterfaceMethodVO.getMethodId());
        applicationInterfaceMethod.setMethodName(applicationInterfaceMethodVO.getMethodName());
        applicationInterfaceMethod.setParameterType(applicationInterfaceMethodVO.getParameterType());
        applicationInterfaceMethod.setUri(applicationInterfaceMethodVO.getUri());
        applicationInterfaceMethod.setHttpCommandType(applicationInterfaceMethodVO.getHttpCommandType());
        applicationInterfaceMethod.setAuth(applicationInterfaceMethodVO.getAuth());
        applicationInterfaceMethodDao.insert(applicationInterfaceMethod);
    }
}
