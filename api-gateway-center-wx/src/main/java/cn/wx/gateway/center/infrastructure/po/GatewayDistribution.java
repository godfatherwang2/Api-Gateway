package cn.wx.gateway.center.infrastructure.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 小傅哥，微信：fustack
 * @description 网关分配
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Data
@TableName("gateway_distribution")
public class GatewayDistribution {

    /** 自增主键 */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /** 分组标识 */
    private String groupId;
    /** 网关标识 */
    private String gatewayId;
    /** 系统标识 */
    private String systemId;
    /** 系统名称 */
    private String systemName;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;


}
