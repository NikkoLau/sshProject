package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.entity.HouseImage;
import com.atguigu.mapper.BaseMapper;
import com.atguigu.mapper.HouseImageMapper;
import com.atguigu.servcie.Impl.BaseServiceImpl;
import com.atguigu.service.HouseImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(interfaceClass = HouseImageService.class)
@Transactional
public class HouseImageServiceImpl extends BaseServiceImpl<HouseImage>  implements HouseImageService {

    @Autowired
    private HouseImageMapper houseImageMapper;

    @Override
    protected BaseMapper<HouseImage> getEntityDao() {
        return houseImageMapper;
    }

    @Override
    public List<HouseImage> getHouseImageByHouseId(Long houseId,Integer type) {
        return houseImageMapper.getHouseImageByHouseId(houseId,type);
    }
}
