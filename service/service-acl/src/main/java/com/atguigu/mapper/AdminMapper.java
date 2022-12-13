package com.atguigu.mapper;

import com.atguigu.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper extends BaseMapper<Admin> {
    Admin getByUsername(@Param("username") String username);


    //增删改

}
