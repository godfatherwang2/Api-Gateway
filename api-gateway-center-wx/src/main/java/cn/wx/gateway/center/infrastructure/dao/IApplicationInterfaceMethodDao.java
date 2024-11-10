package cn.wx.gateway.center.infrastructure.dao;

import cn.wx.gateway.center.domain.operation.model.vo.ApplicationInterfaceMethodDataVO;
import cn.wx.gateway.center.infrastructure.common.OperationRequest;
import cn.wx.gateway.center.infrastructure.po.ApplicationInterfaceMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 小傅哥，微信：fustack
 * @description 应用接口方法
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Mapper
public interface IApplicationInterfaceMethodDao extends BaseMapper<ApplicationInterfaceMethod> {
    List<ApplicationInterfaceMethod> queryApplicationInterfaceMethodList(ApplicationInterfaceMethod req);

    List<ApplicationInterfaceMethod> queryApplicationInterfaceMethodListByPage(OperationRequest<ApplicationInterfaceMethodDataVO> request);

    int queryApplicationInterfaceMethodListCountByPage(OperationRequest<ApplicationInterfaceMethodDataVO> request);
}
