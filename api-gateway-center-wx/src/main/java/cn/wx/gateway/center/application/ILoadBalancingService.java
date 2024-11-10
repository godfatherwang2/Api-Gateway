package cn.wx.gateway.center.application;

import cn.wx.gateway.center.domain.docker.model.aggregates.NginxConfig;

/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/9 下午9:04
 */
public interface ILoadBalancingService {
    void updateNginxConfig(NginxConfig nginxConfig) throws Exception;
}
