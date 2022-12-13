package com.atguigu.mapper;

import com.atguigu.entity.UserFollow;
import com.atguigu.vo.UserFollowVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFollowMapper extends BaseMapper<UserFollow> {
    List<UserFollowVo> findPageList(Long id);

    Integer isFollow(@Param("userId") Long userId, @Param("houseId")Long houseId);
}
