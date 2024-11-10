package cn.wx.gateway.center.interfaces;

import cn.wx.gateway.center.application.IConfigManageService;
import cn.wx.gateway.center.application.ILoadBalancingService;
import cn.wx.gateway.center.application.IMessageService;
import cn.wx.gateway.center.domain.docker.model.aggregates.NginxConfig;
import cn.wx.gateway.center.domain.docker.model.vo.LocationVO;
import cn.wx.gateway.center.domain.docker.model.vo.UpstreamVO;
import cn.wx.gateway.center.domain.manage.model.aggregates.ApplicationSystemRichInfo;
import cn.wx.gateway.center.domain.manage.model.vo.GatewayServerDetailVO;
import cn.wx.gateway.center.domain.manage.model.vo.GatewayServerVO;
import cn.wx.gateway.center.infrastructure.common.ResponseCode;
import cn.wx.gateway.center.infrastructure.common.Result;
import cn.wx.gateway.center.infrastructure.po.GatewayServerDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/6 上午11:20
 */
@RestController
@RequestMapping("/wg/admin/config")
public class GatewayConfigManage {

    private Logger logger = LoggerFactory.getLogger(GatewayConfigManage.class);

    @Resource
    private ILoadBalancingService loadBalancingService;

    @Resource
    private IMessageService messageService;

    @Resource
    private IConfigManageService configManageService;

    @GetMapping(value = "queryServerConfig", produces = "application/json;charset=utf-8")
    public Result<List<GatewayServerVO>> queryServerConfig() {
        try {
            logger.info("查询网关服务配置项信息");
            List<GatewayServerVO> gatewayServerVOList = configManageService.queryGatewayServerList();
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), gatewayServerVOList);
        } catch (Exception e) {
            logger.error("查询网关服务配置项信息异常", e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), null);
        }
    }

    @PostMapping(value = "registerGateway")
    public Result<Boolean> registerGatewayServerNode(@RequestParam String groupId, @RequestParam String gatewayId, @RequestParam String gatewayName, @RequestParam String gatewayAddress) {
        try {
            logger.info("注册网关服务节点 gatewayId：{} gatewayName：{} gatewayAddress：{}", gatewayId, gatewayName, gatewayAddress);
            boolean done = configManageService.registerGatewayServerNode(groupId, gatewayId, gatewayName, gatewayAddress);
            // 2. 读取最新网关算力数据【由于可能来自于多套注册中心，所以从数据库或者Redis中获取，更为准确】
            List<GatewayServerDetailVO> gatewayServerDetailVOList = configManageService.queryGatewayServerDetailList();
            Map<String,List<GatewayServerDetailVO>> gatewayServerDetailMap = gatewayServerDetailVOList.stream().
                    collect(Collectors.groupingBy(GatewayServerDetailVO::getGroupId));
            Set<String> uniqueGroupIdList = gatewayServerDetailMap.keySet();
            // 3.1 Location 信息
            List<LocationVO> locationList = new ArrayList<>();
            for (String name : uniqueGroupIdList) {
                // location /api01/ {
                //     rewrite ^/api01/(.*)$ /$1 break;
                // 	   proxy_pass http://api01;
                // }
                locationList.add(new LocationVO("/" + name + "/", "http://" + name + ";"));
            }
            List<UpstreamVO> upstreamList = new ArrayList<>();
            for(String name : uniqueGroupIdList){
                List<String> servers = gatewayServerDetailMap.get(name).stream()
                        .map(GatewayServerDetailVO::getGatewayAddress)
                        .collect(Collectors.toList());
                upstreamList.add(new UpstreamVO(name, "least_conn;", servers));
            }
            // 4. 刷新Nginx配置
            loadBalancingService.updateNginxConfig(new NginxConfig(upstreamList, locationList));
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), done);
        } catch (Exception e) {
            logger.error("注册网关服务节点异常", e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), false);
        }
    }

    @PostMapping(value = "queryApplicationSystemRichInfo", produces = "application/json;charset=utf-8")
    public Result<ApplicationSystemRichInfo> queryApplicationSystemRichInfo(@RequestParam String gatewayId, @RequestParam String systemId) {
        try {
            logger.info("查询分配到网关下的待注册系统信息(系统、接口、方法) gatewayId：{}", gatewayId);
            ApplicationSystemRichInfo applicationSystemRichInfo = configManageService.queryApplicationSystemRichInfo(gatewayId,systemId);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), applicationSystemRichInfo);
        } catch (Exception e) {
            logger.error("查询分配到网关下的待注册系统信息(系统、接口、方法)异常 gatewayId：{}", gatewayId, e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), null);
        }

    }


    @GetMapping(value = "queryRabbitMQConfig", produces = "application/json;charset=utf-8")
    public Result<Map<String, String>> queryRabbitMQConfig() {
        try {
            logger.info("查询配置中心RabbitMQ配置信息");
            Map<String, String> rabbitMQConfig = messageService.queryRabbitMQConfig();
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), rabbitMQConfig);
        } catch (Exception e) {
            logger.error("查询配置中心RabbitMQ配置信息失败", e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), null);
        }
    }

}
