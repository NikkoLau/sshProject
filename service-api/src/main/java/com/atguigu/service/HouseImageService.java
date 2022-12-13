package com.atguigu.service;

import com.atguigu.entity.HouseImage;
import com.atguigu.servcie.BaseService;

import java.util.List;

public interface HouseImageService extends BaseService<HouseImage> {
    List<HouseImage> getHouseImageByHouseId(Long houseId,Integer type);
}
