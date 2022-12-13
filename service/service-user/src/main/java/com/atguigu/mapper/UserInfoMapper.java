package com.atguigu.mapper;

import com.atguigu.entity.UserInfo;

public interface UserInfoMapper extends BaseMapper<UserInfo> {

    UserInfo getByPhone(String num);
}
