package cn.wx.gateway.center.domain.manage.model.vo;

import lombok.Data;

/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/6 上午9:49
 */

@Data
public class ApiData {

    private String application;
    /** 服务接口；RPC、其他 */
    private String interfaceName;
    /** 服务方法；RPC#method */
    private String methodName;
    /** 参数类型(RPC 限定单参数注册)；new String[]{"java.lang.String"}、new String[]{"cn.bugstack.gateway.rpc.dto.XReq"} */
    private String parameterType;
    /** 网关接口 */
    private String uri;
    /** 接口类型；GET、POST、PUT、DELETE */
    private String httpCommandType;
    /** 是否鉴权；true = 1是、false = 0否 */
    private Integer auth;
}
