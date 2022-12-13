package com.atguigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.entity.UserInfo;
import com.atguigu.mapper.BaseMapper;
import com.atguigu.mapper.UserInfoMapper;
import com.atguigu.servcie.Impl.BaseServiceImpl;
import com.atguigu.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = UserInfoService.class)
@Transactional
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;


    @Override
    protected BaseMapper<UserInfo> getEntityDao() {
        return userInfoMapper;
    }

    @Override
    public UserInfo getByPhone(String num) {
        return userInfoMapper.getByPhone(num);
    }
}
