package cn.wx.gateway.center.domain.docker.service;

import cn.wx.gateway.center.application.ILoadBalancingService;
import cn.wx.gateway.center.domain.docker.model.aggregates.NginxConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/9 下午9:09
 */
public abstract class AbstractLoadBalancing implements ILoadBalancingService {

    private Logger logger = LoggerFactory.getLogger(AbstractLoadBalancing.class);

    @Override
    public void updateNginxConfig(NginxConfig nginxConfig) throws Exception{
        // 1. 创建 Nginx 配置文件
        String containerFilePath = createNginxConfigFile(nginxConfig);
        logger.info("步骤1：创建 Nginx 配置文件 containerFilePath：{}", containerFilePath);
        // 2. 复制 Nginx 配置文件
        // copyDockerFile(nginxConfig.getApplicationName(), containerFilePath, nginxConfig.getLocalNginxPath());
        // logger.info("步骤2：拷贝 Nginx 配置文件 localPath：{}", nginxConfig.getLocalNginxPath());
        // 3. 刷新 Nginx 配置文件
        refreshNginxConfig(nginxConfig.getNginxName());
        logger.info("步骤2：刷新 Nginx 配置文件 Done！");
    }

    protected abstract void refreshNginxConfig(String nginxName) throws InterruptedException, IOException;

    protected abstract String createNginxConfigFile(NginxConfig nginxConfig) throws IOException;

}
