package cn.wx.gateway.center.domain.manage.model.aggregates;
import cn.wx.gateway.center.domain.manage.model.vo.ApplicationSystemVO;

import java.util.List;

/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/6 下午9:29
 */
public class ApplicationSystemRichInfo {
    /** 网关ID */
    private String gatewayId;
    /** 系统列表 */
    private List<ApplicationSystemVO> applicationSystemVOList;

    public ApplicationSystemRichInfo() {
    }

    public ApplicationSystemRichInfo(String gatewayId, List<ApplicationSystemVO> applicationSystemVOList) {
        this.gatewayId = gatewayId;
        this.applicationSystemVOList = applicationSystemVOList;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public List<ApplicationSystemVO> getApplicationSystemVOList() {
        return applicationSystemVOList;
    }

    public void setApplicationSystemVOList(List<ApplicationSystemVO> applicationSystemVOList) {
        this.applicationSystemVOList = applicationSystemVOList;
    }

}
