package cn.wx.gateway.center.domain.manage.model.vo;

/**
 * @author 小傅哥，微信：fustack
 * @description 网关服务配置
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class GatewayServerVO {

    /** 分组标识 */
    private String groupId;
    /** 分组名称 */
    private String groupName;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
