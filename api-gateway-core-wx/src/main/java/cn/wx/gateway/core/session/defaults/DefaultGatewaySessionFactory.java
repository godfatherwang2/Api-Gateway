package cn.wx.gateway.core.session.defaults;


import cn.wx.gateway.core.datasource.DataSource;
import cn.wx.gateway.core.datasource.DataSourceFactory;
import cn.wx.gateway.core.datasource.unpooled.UnpooledDataSourceFactory;
import cn.wx.gateway.core.executor.Executor;
import cn.wx.gateway.core.session.Configuration;
import cn.wx.gateway.core.session.GatewaySession;
import cn.wx.gateway.core.session.GatewaySessionFactory;

/**
 * @author 小傅哥，微信：fustack
 * @description 默认网关会话工厂
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class DefaultGatewaySessionFactory implements GatewaySessionFactory {

    private final Configuration configuration;

    public DefaultGatewaySessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public GatewaySession openSession(String uri) {
        // 获取数据源连接信息：这里把 Dubbo、HTTP 抽象为一种连接资源
        DataSourceFactory dataSourceFactory = new UnpooledDataSourceFactory();
        dataSourceFactory.setProperties(configuration, uri);
        DataSource dataSource = dataSourceFactory.getDataSource();
        // 创建执行器
        Executor executor = configuration.newExecutor(dataSource.getConnection());
        // 创建会话：DefaultGatewaySession
        return new DefaultGatewaySession(configuration, uri, executor);
    }

}
