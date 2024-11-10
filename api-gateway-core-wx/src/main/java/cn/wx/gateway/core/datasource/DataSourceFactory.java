package cn.wx.gateway.core.datasource;


import cn.wx.gateway.core.session.Configuration;

/**
 * @author 小傅哥，微信：fustack
 * @description 数据源工厂
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public interface DataSourceFactory {

    void setProperties(Configuration configuration, String uri);

    DataSource getDataSource();

}
