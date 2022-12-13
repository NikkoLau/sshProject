package com.atguigu.mapper;

import com.atguigu.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role>{

    List<Role> findAll();

}
