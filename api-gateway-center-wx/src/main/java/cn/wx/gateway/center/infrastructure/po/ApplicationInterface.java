package cn.wx.gateway.center.infrastructure.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


@Data
@TableName("application_interface")
public class ApplicationInterface {

    /** 自增ID */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /** 系统标识 */
    private String systemId;
    /** 接口标识 */
    private String interfaceId;
    /** 接口名称 */
    private String interfaceName;
    /** 接口版本 */
    private String interfaceVersion;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;


}
