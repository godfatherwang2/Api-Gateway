package cn.wx.gateway.center.domain.register.service;

import cn.wx.gateway.center.application.IRegisterManageService;
import cn.wx.gateway.center.domain.register.repository.IRegisterManageRepository;
import cn.wx.gateway.center.domain.register.vo.ApplicationInterfaceMethodVO;
import cn.wx.gateway.center.domain.register.vo.ApplicationInterfaceVO;
import cn.wx.gateway.center.domain.register.vo.ApplicationSystemVO;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/6 下午9:38
 */
@Service
public class RegisterManageService implements IRegisterManageService {

    @Resource
    IRegisterManageRepository registerManageRepository;

    @Override
    public void registerApplication(ApplicationSystemVO applicationSystemVO) {
        registerManageRepository.registerApplication(applicationSystemVO);
    }

    @Override
    public void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO) {
        registerManageRepository.registerApplicationInterface(applicationInterfaceVO);
    }

    @Override
    public void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO) {
        registerManageRepository.registerApplicationInterfaceMethod(applicationInterfaceMethodVO);
    }
}
