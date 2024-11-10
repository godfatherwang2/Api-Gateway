package cn.wx.gateway.center.domain.manage.repository;

import cn.wx.gateway.center.domain.manage.model.vo.ApiData;

import java.util.List;

/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/6 上午9:54
 */
public interface IApiRepository {
    List<ApiData> queryHttpStatementList();
}
