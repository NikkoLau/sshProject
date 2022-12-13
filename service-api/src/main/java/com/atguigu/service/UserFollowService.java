package com.atguigu.service;

import com.atguigu.entity.UserFollow;
import com.atguigu.servcie.BaseService;
import com.atguigu.vo.UserFollowVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserFollowService extends BaseService<UserFollow> {
    PageInfo<UserFollowVo> findPageList(Integer pageNum, Integer pageSize,Long id);

    boolean isFollow(Long userId, Long houseId);
}
