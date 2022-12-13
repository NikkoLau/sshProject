package com.atguigu.mapper;

import com.atguigu.entity.HouseImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseImageMapper extends BaseMapper<HouseImage> {

    List<HouseImage> getHouseImageByHouseId(@Param("houseId") Long houseId,@Param("type")Integer type);
}
