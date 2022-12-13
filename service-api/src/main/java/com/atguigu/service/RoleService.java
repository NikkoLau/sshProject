package com.atguigu.service;

import com.atguigu.entity.AdminRole;
import com.atguigu.entity.Role;
import com.atguigu.servcie.BaseService;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface RoleService extends BaseService<Role> {
    List<Role> findAll();

    Map<String,Object> assginRoleById(Long id);

    void addRole(Long adminId, Long[] roleIds);
}
