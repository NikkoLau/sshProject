package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.UserFollow;
import com.atguigu.entity.UserInfo;
import com.atguigu.result.Result;
import com.atguigu.result.ResultCodeEnum;
import com.atguigu.service.UserFollowService;
import com.atguigu.vo.UserFollowVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/userFollow")
public class UserFollowController {

    @Reference
    private UserFollowService userFollowService;

    @RequestMapping("/auth/list/{pageNum}/{pageSize}")
    public Result follow(@PathVariable("pageNum")Integer pageNum, @PathVariable("pageSize")Integer pageSize, HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("user");
        PageInfo<UserFollowVo> userFollow = userFollowService.findPageList(pageNum,pageSize,user.getId());
        return Result.ok(userFollow);
    }
    @RequestMapping("/auth/follow/{houseId}")
    public Result followHouse(@PathVariable("houseId") Long houseId,HttpSession httpSession){
        UserInfo user = (UserInfo) httpSession.getAttribute("user");
        Long userId = user.getId();
        UserFollow userFollow = new UserFollow();
        userFollow.setHouseId(houseId);
        userFollow.setUserId(userId);
        Integer insert = userFollowService.insert(userFollow);
        if (insert >0){
            //关注成功
            return Result.ok();
        }
        return Result.build(null, ResultCodeEnum.DATA_ERROR);
    }
    @RequestMapping("/auth/cancelFollow/{id}")
    public Result cancelFollow(@PathVariable("id")Long id){
        userFollowService.delete(id);
        return Result.ok();
    }
}
