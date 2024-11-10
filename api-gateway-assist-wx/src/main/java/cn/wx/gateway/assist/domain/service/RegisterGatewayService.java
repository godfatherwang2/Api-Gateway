package cn.wx.gateway.assist.domain.service;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.wx.gateway.assist.GatewayException;
import cn.wx.gateway.assist.domain.model.aggregates.ApplicationSystemRichInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.wx.gateway.assist.common.Result;
import java.util.HashMap;
import java.util.Map;
/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/6 下午8:58
 */
public class RegisterGatewayService {

    private Logger logger = LoggerFactory.getLogger(RegisterGatewayService.class);

    public void doRegister(String address, String groupId, String gatewayId, String gatewayName, String gatewayAddress){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("groupId", groupId);
        paramMap.put("gatewayId", gatewayId);
        paramMap.put("gatewayName", gatewayName);
        paramMap.put("gatewayAddress", gatewayAddress);
        String resultStr;
        try {
            resultStr = HttpUtil.post(address + "/wg/admin/config/registerGateway", paramMap, 2000);
        } catch (Exception e) {
            logger.error("网关服务注册异常，链接资源不可用：{}", address + "/wg/admin/config/registerGateway");
            throw e;
        }
        Result<Boolean> result = JSON.parseObject(resultStr, new TypeReference<Result<Boolean>>() {
        });
        logger.info("向网关中心注册网关算力服务 gatewayId：{} gatewayName：{} gatewayAddress：{} 注册结果：{}", gatewayId, gatewayName, gatewayAddress, resultStr);
        if (!"0000".equals(result.getCode()))
            throw new GatewayException("网关服务注册异常 [gatewayId：" + gatewayId + "] 、[gatewayAddress：" + gatewayAddress + "]");
    }

    public ApplicationSystemRichInfo pullApplicationSystemRichInfo(String address, String gatewayId){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gatewayId",gatewayId);
        String resultStr;
        try {
            resultStr = HttpUtil.post(address + "/wg/admin/config/queryApplicationSystemRichInfo", paramMap, 2000);
        } catch (Exception e) {
            logger.error("网关服务拉取异常，链接资源不可用：{}", address + "/wg/admin/config/queryApplicationSystemRichInfo");
            throw e;
        }
        Result<ApplicationSystemRichInfo> result = JSON.parseObject(resultStr, new TypeReference<Result<ApplicationSystemRichInfo>>() {
        });
        logger.info("从网关中心拉取应用服务和接口的配置信息到本地完成注册。gatewayId：{}", gatewayId);
        if (!"0000".equals(result.getCode()))
            throw new GatewayException("从网关中心拉取应用服务和接口的配置信息到本地完成注册异常 [gatewayId：" + gatewayId + "]");
        return result.getData();
    }
    public Map<String,String> queryRedisConfig(String address){
        String rstStr = HttpUtil.get(address+"/wg/admin/config");
        Map<String,String> redisConfig = JSON.parseObject(rstStr,new TypeReference<Map<String,String>>(){});
        return redisConfig;
    }

    public ApplicationSystemRichInfo pullApplicationSystemRichInfo(String address, String gatewayId, String systemId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gatewayId", gatewayId);
        paramMap.put("systemId", systemId);
        String resultStr;
        try {
            resultStr = HttpUtil.post(address + "/wg/admin/config/queryApplicationSystemRichInfo", paramMap, 3000);
        } catch (Exception e) {
            logger.error("网关服务拉取异常，链接资源不可用：{}", address + "/wg/admin/config/queryApplicationSystemRichInfo", e);
            throw e;
        }
        Result<ApplicationSystemRichInfo> result = JSON.parseObject(resultStr, new TypeReference<Result<ApplicationSystemRichInfo>>() {
        });
        logger.info("从网关中心拉取应用服务和接口的配置信息到本地完成注册。gatewayId：{}", gatewayId);
        if (!"0000".equals(result.getCode()))
            throw new GatewayException("从网关中心拉取应用服务和接口的配置信息到本地完成注册异常 [gatewayId：" + gatewayId + "]");
        return result.getData();
    }

    public Map<String,String> queryRabbitMQConfig(String address) {
        String rstStr = HttpUtil.get(address+"/wg/admin/config/queryRabbitMQConfig");
        Result<Map<String,String>> redisConfig = JSON.parseObject(rstStr,new TypeReference<Result<Map<String,String>>>(){});
        return redisConfig.getData();
    }
}
