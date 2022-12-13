package com.atguigu.service;

import com.atguigu.entity.HouseUser;
import com.atguigu.servcie.BaseService;

import java.util.List;

public interface HouseUserService extends BaseService<HouseUser> {
    List<HouseUser> getHouseUserByHouseId(Long houseId);
}
