package cn.wx.gateway.center.infrastructure.dao;
import cn.wx.gateway.center.domain.operation.model.vo.GatewayServerDetaiDatalVO;
import cn.wx.gateway.center.infrastructure.common.OperationRequest;
import cn.wx.gateway.center.infrastructure.po.GatewayServerDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 小傅哥，微信：fustack
 * @description
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Mapper
public interface IGatewayServerDetailDao extends BaseMapper<GatewayServerDetail> {

    List<GatewayServerDetail> queryGatewayServerDetailListByPage(OperationRequest<GatewayServerDetaiDatalVO> request);

    int queryGatewayServerDetailListCountByPage(OperationRequest<GatewayServerDetaiDatalVO> request);
}
