package cn.wx.gateway.center.infrastructure.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 小傅哥，微信：fustack
 * @description 应用接口方法
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Data
@TableName("application_interface_method")
public class ApplicationInterfaceMethod {

    /** 自增ID */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /** 系统标识 */
    private String systemId;
    /** 接口标识 */
    private String interfaceId;
    /** 方法标识 */
    private String methodId;
    /** 方法名称 */
    private String methodName;
    /** 参数类型(RPC 限定单参数注册)；new String[]{"java.lang.String"}、new String[]{"cn.bugstack.gateway.rpc.dto.XReq"} */
    private String parameterType;
    /** 网关接口 */
    private String uri;
    /** 接口类型；GET、POST、PUT、DELETE */
    private String httpCommandType;
    /** 是否鉴权；true = 1是、false = 0否 */
    private Integer auth;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

}
