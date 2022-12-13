package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.HouseUser;
import com.atguigu.service.HouseUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/houseUser")
public class HouseUserController {
    @Reference
    private HouseUserService houseUserService;

    @RequestMapping("/create")
    public String create(@RequestParam("houseId")Long houseId, Map map){
        map.put("houseId",houseId);
        return "houseUser/create";
    }

    @PostMapping("/save")
    public String save(HouseUser houseUser){
        houseUserService.insert(houseUser);
        return "common/successPage";
    }

    @RequestMapping("/edit/{id}")
    public String toUpdate(@PathVariable("id")Long id, ModelMap  modelMap){
        HouseUser houseUser = houseUserService.getById(id);
        modelMap.addAttribute("houseUser",houseUser);
        return "houseUser/edit";
    }

    @PostMapping("/update")
    public String update(HouseUser houseUser){
        houseUserService.update(houseUser);
        return "common/successPage";
    }

    @RequestMapping("/delete/{houseId}/{id}")
    public String delete(@PathVariable("houseId")Long houseId,@PathVariable("id") Long id){
        houseUserService.delete(id);
        return "redirect:/house/"+houseId;
    }
}
