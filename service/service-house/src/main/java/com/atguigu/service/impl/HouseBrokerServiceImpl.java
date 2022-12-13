package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.entity.HouseBroker;
import com.atguigu.mapper.BaseMapper;
import com.atguigu.mapper.HouseBrokerMapper;
import com.atguigu.servcie.BaseService;
import com.atguigu.servcie.Impl.BaseServiceImpl;
import com.atguigu.service.HouseBrokerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = HouseBrokerService.class)
@Transactional
public class HouseBrokerServiceImpl extends BaseServiceImpl<HouseBroker> implements HouseBrokerService {

    @Autowired
    private HouseBrokerMapper houseBrokerMapper;

    @Override
    protected BaseMapper<HouseBroker> getEntityDao() {
        return houseBrokerMapper;
    }

    @Override
    public List<HouseBroker> getBokerByHouseId(Long id) {
        return houseBrokerMapper.getBokerByHouseId(id);
    }

    @Override
    public void deleteByhouseIdAndBrokerId(Long houseId, Long id) {
        houseBrokerMapper.deleteByhouseIdAndBrokerId( houseId, id);
    }

}
