package com.atguigu.service;

import com.atguigu.entity.House;
import com.atguigu.servcie.BaseService;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface HouseService extends BaseService<House> {
    PageInfo<HouseVo> findPageFront(Integer pageNum, Integer pageSize, HouseQueryVo houseQueryVo);
}
