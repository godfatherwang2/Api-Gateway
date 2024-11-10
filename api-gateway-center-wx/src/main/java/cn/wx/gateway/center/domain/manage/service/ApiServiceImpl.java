package cn.wx.gateway.center.domain.manage.service;

import cn.wx.gateway.center.application.IApiService;
import cn.wx.gateway.center.domain.manage.model.vo.ApiData;
import cn.wx.gateway.center.domain.manage.repository.IApiRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/6 上午9:54
 */

@Service
public class ApiServiceImpl implements IApiService {

    @Resource
    IApiRepository apiRepository;

    @Override
    public List<ApiData> queryHttpStatementList() {
        return apiRepository.queryHttpStatementList();
    }
}
