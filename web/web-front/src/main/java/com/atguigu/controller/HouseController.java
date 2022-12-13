package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.*;
import com.atguigu.result.Result;
import com.atguigu.service.*;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/house")
public class HouseController {
    @Reference
    private HouseService houseService;

    @Reference
    private CommunityService communityService;
    @Reference
    private HouseBrokerService houseBrokerService;
    @Reference
    private HouseImageService houseImageService;

    @Reference
    private UserFollowService userFollowService;

    @RequestMapping("/list/{pageNum}/{pageSize}")
    public Result index(@PathVariable("pageNum")Integer pageNum, @PathVariable("pageSize")Integer pageSize,
                        @RequestBody HouseQueryVo houseQueryVo){
        PageInfo<HouseVo> PageInfo = houseService.findPageFront(pageNum, pageSize, houseQueryVo);
        return Result.ok(PageInfo);
    }

    @RequestMapping("/info/{id}")
    public Result detail(@PathVariable("id")Long id,HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("user");
        House house = houseService.getById(id);
        Community community = communityService.getById(house.getCommunityId());
        List<HouseBroker> houseBrokerList = houseBrokerService.getBokerByHouseId(id);
        List<HouseImage> houseImage1List = houseImageService.getHouseImageByHouseId(id, 1);
        Map<String, Object> map = new HashMap<>();
        map.put("house",house);
        map.put("community",community);
        map.put("houseBrokerList",houseBrokerList);
        map.put("houseImage1List",houseImage1List);
        boolean isFollow = false;
        if (user!=null ){
            isFollow = userFollowService.isFollow(user.getId(),id);
        }
        map.put("isFollow",isFollow);
        return Result.ok(map);
    }
}
