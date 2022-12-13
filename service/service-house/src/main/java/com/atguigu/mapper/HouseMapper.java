package com.atguigu.mapper;

import com.atguigu.entity.House;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;

import java.util.List;

public interface HouseMapper extends BaseMapper<House> {
    List<HouseVo> findPageFront(HouseQueryVo houseQueryVo);
}
