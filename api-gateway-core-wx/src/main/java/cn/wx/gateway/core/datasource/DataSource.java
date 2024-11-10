package cn.wx.gateway.core.datasource;

/**
 * @author 小傅哥，微信：fustack
 * @description 数据源接口，RPC、HTTP 都当做连接的数据资源使用
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public interface DataSource {

    Connection getConnection();

}