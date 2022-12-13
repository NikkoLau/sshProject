package com.atguigu.mapper;

import com.atguigu.entity.AdminRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminRoleMapper extends BaseMapper<AdminRole> {
    List<Long> findRoleById(@Param("id") Long id);

    void deleteByAdminId(Long adminId);
}
