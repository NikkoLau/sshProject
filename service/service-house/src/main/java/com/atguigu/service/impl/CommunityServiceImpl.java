package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.entity.Community;
import com.atguigu.mapper.BaseMapper;
import com.atguigu.mapper.CommunityMapper;
import com.atguigu.mapper.DictMapper;
import com.atguigu.servcie.BaseService;
import com.atguigu.servcie.Impl.BaseServiceImpl;
import com.atguigu.service.CommunityService;
import com.atguigu.utils.CastUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CommunityService.class)
@Transactional
public class CommunityServiceImpl extends BaseServiceImpl<Community> implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private DictMapper dictMapper;

    @Override
    protected BaseMapper<Community> getEntityDao() {

        return communityMapper;
    }

    @Override
    public PageInfo<Community> findPage(Map<String, Object> filters) {
        //当前页数
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        //每页显示的记录条数
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 3);

        PageHelper.startPage(pageNum, pageSize);
        Page<Community> page = getEntityDao().findPage(filters);
        for (Community community : page) {
            String areaName = dictMapper.getNameById(community.getAreaId());
            String plateName = dictMapper.getNameById(community.getPlateId());
            community.setAreaName(areaName);
            community.setPlateName(plateName);
        }
        return new PageInfo<>(page,10);
    }

    @Override
    public List<Community> findAll() {
        return communityMapper.findAll();
    }

    @Override
    public Community getById(Serializable id) {
        Community community = super.getById(id);
        String areaName = dictMapper.getNameById(community.getAreaId());
        String plateName = dictMapper.getNameById(community.getPlateId());
        community.setAreaName(areaName);
        community.setPlateName(plateName);
        return community;
    }
}
