package com.atguigu.mapper;

import com.atguigu.entity.HouseBroker;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseBrokerMapper extends BaseMapper<HouseBroker> {
    List<HouseBroker> getBokerByHouseId(Long id);
    void deleteByhouseIdAndBrokerId(@Param("houseId")Long houseId,@Param("id")Long id);
}
