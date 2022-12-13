package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Admin;
import com.atguigu.entity.HouseBroker;
import com.atguigu.service.AdminService;
import com.atguigu.service.HouseBrokerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/houseBroker")
public class HouseBrokerController {

    @Reference
    private AdminService adminService;

    @Reference
    private HouseBrokerService houseBrokerService;

    @RequestMapping("/create")
    public String toCreate(@RequestParam("houseId") Long houseId, Map map){
        List<Admin> adminList = adminService.getAll();
        map.put("adminList",adminList);
        map.put("houseId",houseId);
        return "houseBroker/create";

    }

    @PostMapping("/save")
    public String save(HouseBroker houseBroker){
        Admin broker = adminService.getById(houseBroker.getBrokerId());
        houseBroker.setBrokerName(broker.getName());
        houseBroker.setBrokerHeadUrl(broker.getHeadUrl());
        houseBrokerService.insert(houseBroker);
        return "common/successPage";
    }

    @RequestMapping("/edit/{id}")
    public String toUpdate(@PathVariable("id")Long id, ModelMap modelMap){
        HouseBroker houseBroker = houseBrokerService.getById(id);
        List<Admin> adminList = adminService.getAll();
        modelMap.addAttribute("houseBroker",houseBroker);
        modelMap.addAttribute("adminList",adminList);
        return "houseBroker/edit";
    }

    @PostMapping("/update")
    public String update(HouseBroker houseBroker){
        Admin broker = adminService.getById(houseBroker.getBrokerId());
        houseBroker.setBrokerName(broker.getName());
        houseBroker.setBrokerHeadUrl(broker.getHeadUrl());
        houseBrokerService.update(houseBroker);
        return "common/successPage";

    }

    @RequestMapping("/delete/{houseId}/{id}")
    public String delete(@PathVariable("houseId")Long houseId ,@PathVariable("id")Long id){
        houseBrokerService.deleteByhouseIdAndBrokerId(houseId,id);
        return "redirect:/house/"+houseId;
    }

}
