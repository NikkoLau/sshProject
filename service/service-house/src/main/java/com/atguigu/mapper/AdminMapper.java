package com.atguigu.mapper;

import com.atguigu.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper extends BaseMapper<Admin> {
    List<Admin> getAll();

    Admin getByUsername(@Param("username") String username);
}
