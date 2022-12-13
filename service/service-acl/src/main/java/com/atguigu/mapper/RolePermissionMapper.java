package com.atguigu.mapper;

import com.atguigu.entity.Permission;
import com.atguigu.entity.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    List<Long> findAllByRoleId(@Param("roleId") Long roleId);

    List<Permission> findPermissByAdminId(@Param("adminId") Long adminId);
}
