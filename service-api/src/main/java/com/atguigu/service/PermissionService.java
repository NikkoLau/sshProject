package com.atguigu.service;

import com.atguigu.entity.Permission;
import com.atguigu.servcie.BaseService;

import java.util.List;
import java.util.Map;

public interface PermissionService extends BaseService<Permission> {
    List<Map<String,Object>> findAll(Long id);

    List<Permission> findMenuPermissionByAdminId(Long id);

    List<Permission> findAllMenu();
}
