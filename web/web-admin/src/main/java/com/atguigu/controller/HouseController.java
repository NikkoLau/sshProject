package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.*;
import com.atguigu.service.*;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/house")
public class HouseController extends BaseController{

    @Reference
    private HouseService houseService;
    @Reference
    private CommunityService communityService;

    @Reference
    private DictService dictService;

    @Reference
    private HouseBrokerService houseBrokerService;
    @Reference
    private HouseUserService houseUserService;
    @Reference
    private HouseImageService houseImageService;

    @RequestMapping
    public String index(Map map, HttpServletRequest httpServletRequest){
        //分页及带条件查询
        Map<String, Object> filters = getFilters(httpServletRequest);
        base(map);
        PageInfo page = houseService.findPage(filters);
        map.put("filters",filters);
        map.put("page",page);
        return "house/index";
    }
    @RequestMapping("/create")
    public String toCreate(Map map){
        base(map);
        return "house/create";
    }

    @RequestMapping("/save")
    public String save(House house){
        houseService.insert(house);
        return "common/successPage";
    }

    @RequestMapping("/edit/{id}")
    public String toEdit(@PathVariable("id")Long id,Map map){
        House house = houseService.getById(id);
        base(map);
        map.put("house",house);
        return "house/edit";
    }

    @RequestMapping("/update")
    public String update(House house){
        houseService.update(house);
        return "common/successPage";
    }


    @RequestMapping("/publish/{id}/{status}")
    public String publish(@PathVariable("id")Long id,@PathVariable("status") Integer status){
        House house = new House();
        house.setId(id);
        house.setStatus(status);
        houseService.update(house);
        return "redirect:/house";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id){
        houseService.delete(id);
        return "redirect:/house";
    }

    @RequestMapping("/{id}")
    public String houseDetail(@PathVariable("id")Long id,Map map){
        House house = houseService.getById(id);
        Community community = communityService.getById(house.getCommunityId());
        // 添加房源经纪人信息
        List<HouseBroker> houseBrokerList = houseBrokerService.getBokerByHouseId(id);
        List<HouseUser> houseUserList = houseUserService.getHouseUserByHouseId(id);
        List<HouseImage> houseImage1List = houseImageService.getHouseImageByHouseId(id,1);
        List<HouseImage> houseImage2List = houseImageService.getHouseImageByHouseId(id,2);
        System.out.println(houseImage1List);
        System.out.println(houseImage2List);
        map.put("house",house);
        map.put("community",community);
        map.put("houseBrokerList",houseBrokerList);
        map.put("houseUserList",houseUserList);
        map.put("houseImage1List",houseImage1List);
        map.put("houseImage2List",houseImage2List);
        return "house/detail";
    }


    private Map<String,Object> base(Map map){
        List<Community> communityList = communityService.findAll();
        List<Dict> houseTypeList = dictService.findListByDictCode("houseType");
        List<Dict> floorList = dictService.findListByDictCode("floor");
        List<Dict> buildStructureList = dictService.findListByDictCode("buildStructure");
        List<Dict> directionList = dictService.findListByDictCode("direction");
        List<Dict> decorationList = dictService.findListByDictCode("decoration");
        List<Dict> houseUseList = dictService.findListByDictCode("houseUse");
        map.put("communityList", communityList);
        map.put("houseTypeList", houseTypeList);
        map.put("floorList", floorList);
        map.put("buildStructureList", buildStructureList);
        map.put("directionList", directionList);
        map.put("decorationList", decorationList);
        map.put("houseUseList", houseUseList);
        return map;

    }

}
