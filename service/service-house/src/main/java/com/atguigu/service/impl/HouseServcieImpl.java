package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.entity.House;
import com.atguigu.mapper.BaseMapper;
import com.atguigu.mapper.HouseMapper;
import com.atguigu.servcie.Impl.BaseServiceImpl;
import com.atguigu.service.DictService;
import com.atguigu.service.HouseService;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = HouseService.class)
@Transactional
public class HouseServcieImpl extends BaseServiceImpl<House> implements HouseService {

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private DictService dictService;

    @Override
    protected BaseMapper getEntityDao() {
        return houseMapper;
    }

    @Override
    public House getById(Serializable id) {
        House house = super.getById(id);
        String HouseTypeName = dictService.getNameById(house.getHouseTypeId());
        String floorName = dictService.getNameById(house.getFloorId());
        String buildStructureName= dictService.getNameById(house.getBuildStructureId());
        String  directionName = dictService.getNameById(house.getDirectionId());
        String decorationName =  dictService.getNameById(house.getDecorationId());
        String houseUseName =  dictService.getNameById(house.getHouseUseId());
        house.setHouseTypeName(HouseTypeName);
        house.setFloorName(floorName);
        house.setBuildStructureName(buildStructureName);
        house.setDirectionName(directionName);
        house.setDecorationName(decorationName);
        house.setHouseUseName(houseUseName);

        return house;
    }

    @Override
    public PageInfo<HouseVo> findPageFront(Integer pageNum, Integer pageSize, HouseQueryVo houseQueryVo) {

        PageHelper.startPage(pageNum,pageSize);
        List<HouseVo> list = houseMapper.findPageFront(houseQueryVo);
//        private Long houseTypeId;
//        private Long floorId;
//        private Long directionId;
        for (HouseVo houseVo : list) {
            String houseTypeName = dictService.getNameById(houseVo.getHouseTypeId());
            houseVo.setHouseTypeName(houseTypeName);
            String floorName = dictService.getNameById(houseVo.getFloorId());
            houseVo.setFloorName(floorName);
            String directionName = dictService.getNameById(houseVo.getDirectionId());
            houseVo.setDirectionName(directionName);
        }
        return new PageInfo<>(list,5);
    }
}
