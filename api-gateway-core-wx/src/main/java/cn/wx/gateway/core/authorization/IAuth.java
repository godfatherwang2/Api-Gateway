package cn.wx.gateway.core.authorization;

/**
 * @author 小傅哥，微信：fustack
 * @description 认证服务接口
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public interface IAuth {

    boolean validate(String id, String token);

}