package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Permission;
import com.atguigu.entity.RolePermission;
import com.atguigu.mapper.BaseMapper;
import com.atguigu.mapper.PermissionMapper;
import com.atguigu.mapper.RolePermissionMapper;
import com.atguigu.servcie.Impl.BaseServiceImpl;
import com.atguigu.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;


public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission> implements RolePermissionService {
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Reference
    private PermissionMapper permissionMapper;

    @Override
    protected BaseMapper<RolePermission> getEntityDao() {
        return rolePermissionMapper;
    }


}
