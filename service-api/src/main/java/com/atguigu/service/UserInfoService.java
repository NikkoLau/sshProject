package com.atguigu.service;

import com.atguigu.entity.UserInfo;
import com.atguigu.servcie.BaseService;

public interface UserInfoService extends BaseService<UserInfo> {
    UserInfo getByPhone(String num);
}
