package com.atguigu.service;

import com.atguigu.entity.Admin;
import com.atguigu.servcie.BaseService;

import java.util.List;

public interface AdminService extends BaseService<Admin> {
    List<Admin> getAll();

    Admin getByUsername(String username);
}
