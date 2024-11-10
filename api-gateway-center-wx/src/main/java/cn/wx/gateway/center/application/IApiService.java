package cn.wx.gateway.center.application;

import cn.wx.gateway.center.domain.manage.model.vo.ApiData;

import java.util.List;

/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/6 上午9:53
 */
public interface IApiService {

    List<ApiData> queryHttpStatementList();

}