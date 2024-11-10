package cn.wx.gateway.center.application;

import cn.wx.gateway.center.domain.register.vo.ApplicationInterfaceMethodVO;
import cn.wx.gateway.center.domain.register.vo.ApplicationInterfaceVO;
import cn.wx.gateway.center.domain.register.vo.ApplicationSystemVO;

/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/6 下午9:29
 */
public interface IRegisterManageService {
    void registerApplication(ApplicationSystemVO applicationSystemVO);

    void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO);

    void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO);
}
