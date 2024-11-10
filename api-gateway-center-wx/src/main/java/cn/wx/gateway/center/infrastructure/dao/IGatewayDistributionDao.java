package cn.wx.gateway.center.infrastructure.dao;

import cn.wx.gateway.center.domain.operation.model.vo.GatewayDistributionDataVO;
import cn.wx.gateway.center.infrastructure.common.OperationRequest;
import cn.wx.gateway.center.infrastructure.po.GatewayDistribution;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:TODO
 * @author: Lenovo
 * @create: 2024/11/7 上午8:49
 */
@Mapper
public interface IGatewayDistributionDao extends BaseMapper<GatewayDistribution> {
    List<GatewayDistribution> queryGatewayDistributionListByPage(OperationRequest<GatewayDistributionDataVO> request);

    int queryGatewayDistributionListCountByPage(OperationRequest<GatewayDistributionDataVO> request);
}
