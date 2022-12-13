package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.entity.HouseUser;
import com.atguigu.mapper.BaseMapper;
import com.atguigu.mapper.HouseUserMapper;
import com.atguigu.servcie.Impl.BaseServiceImpl;
import com.atguigu.service.HouseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(interfaceClass = HouseUserService.class)
@Transactional
public class HouseUserServiceImpl extends BaseServiceImpl<HouseUser> implements HouseUserService {

    @Autowired
    private HouseUserMapper houseUserMapper;
    @Override
    protected BaseMapper<HouseUser> getEntityDao() {
        return houseUserMapper;
    }

    @Override
    public List<HouseUser> getHouseUserByHouseId(Long houseId) {
        return houseUserMapper.getHouseUserByHouseId(houseId);
    }
}
