package com.atguigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.entity.UserFollow;
import com.atguigu.mapper.BaseMapper;
import com.atguigu.mapper.UserFollowMapper;
import com.atguigu.servcie.Impl.BaseServiceImpl;
import com.atguigu.service.DictService;
import com.atguigu.service.UserFollowService;
import com.atguigu.vo.UserFollowVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = UserFollowService.class)
@Transactional
public class UserFollowServiceImpl extends BaseServiceImpl<UserFollow> implements UserFollowService {

    @Autowired
    private UserFollowMapper userFollowMapper;

    @Reference
    private DictService dictService;

    @Override
    protected BaseMapper<UserFollow> getEntityDao() {
        return userFollowMapper;
    }

    @Override
    public PageInfo<UserFollowVo> findPageList(Integer pageNum, Integer pageSize,Long id) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserFollowVo> follows = userFollowMapper.findPageList(id);
        for (UserFollowVo follow : follows) {
            String houseTypeName = dictService.getNameById(follow.getHouseTypeId());
            follow.setHouseTypeName(houseTypeName);
            String followName = dictService.getNameById(follow.getFloorId());
            follow.setFloorName(followName);
            String dictServiceName = dictService.getNameById(follow.getDirectionId());
            follow.setDirectionName(dictServiceName);
        }
        PageInfo<UserFollowVo> pageInfo = new PageInfo(follows);
        return pageInfo;
    }

    @Override
    public boolean isFollow(Long userId, Long houseId) {
        Integer count = userFollowMapper.isFollow(userId, houseId);
        if (count>0){
            return true;
        }
        return false;
    }
}
