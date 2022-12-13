package com.atguigu.service;

import com.atguigu.entity.HouseBroker;
import com.atguigu.servcie.BaseService;

import java.util.List;

public interface HouseBrokerService extends BaseService<HouseBroker> {
    List<HouseBroker> getBokerByHouseId(Long id);
    void deleteByhouseIdAndBrokerId(Long houseId,Long id);
}
