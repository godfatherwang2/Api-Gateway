package cn.wx.gateway.center.infrastructure.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 小傅哥，微信：fustack
 * @description 应用系统
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Data
@TableName("application_system")
public class ApplicationSystem {

    /** 自增ID */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /** 系统标识 */
    private String systemId;
    /** 系统名称 */
    private String systemName;
    /** 系统类型；RPC、HTTP*/
    private String systemType;
    /** 注册中心；zookeeper://127.0.0.1:2181*/
    private String systemRegistry;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;


}
