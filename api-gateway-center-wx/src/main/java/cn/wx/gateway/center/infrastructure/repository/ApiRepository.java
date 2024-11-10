package cn.wx.gateway.center.infrastructure.repository;


import cn.wx.gateway.center.domain.manage.model.vo.ApiData;
import cn.wx.gateway.center.domain.manage.repository.IApiRepository;
import cn.wx.gateway.center.infrastructure.dao.IHttpStatementDao;
import cn.wx.gateway.center.infrastructure.po.HttpStatement;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 小傅哥，微信：fustack
 * @description 仓储实现
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Component
public class ApiRepository implements IApiRepository {

    @Resource
    private IHttpStatementDao httpStatementDao;

    @Override
    public List<ApiData> queryHttpStatementList() {
        List<HttpStatement> httpStatements = httpStatementDao.selectList(null);
        List<ApiData> apiDataList = new ArrayList<>(httpStatements.size());
        for (HttpStatement httpStatement : httpStatements) {
            ApiData apiData = new ApiData();
            apiData.setApplication(httpStatement.getApplication());
            apiData.setInterfaceName(httpStatement.getInterfaceName());
            apiData.setMethodName(httpStatement.getMethodName());
            apiData.setParameterType(httpStatement.getParameterType());
            apiData.setUri(httpStatement.getUri());
            apiData.setHttpCommandType(httpStatement.getHttpCommandType());
            apiData.setAuth(httpStatement.getAuth());
            apiDataList.add(apiData);
        }
        return apiDataList;
    }

}
