package cn.wx.gateway.assist.test;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import cn.wx.gateway.assist.common.Result;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 小傅哥，微信：fustack
 * @description 单元测试
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ApiTest {

    public static void main(String[] args) {
        System.out.println("Hi Api Gateway");
    }

    @Test
    public void test_register_gateway() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("groupId", "10001");
        paramMap.put("gatewayId", "api-gateway-g4");
        paramMap.put("gatewayName", "电商配送网关");
        paramMap.put("gatewayAddress", "127.0.0.1");

        String resultStr = HttpUtil.post("http://localhost:8081/wg/admin/config/registerGateway", paramMap, 20000);
        System.out.println(resultStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        System.out.println(result.getCode());
    }

}
