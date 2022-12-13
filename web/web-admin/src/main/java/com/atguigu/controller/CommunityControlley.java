package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Community;
import com.atguigu.entity.Dict;
import com.atguigu.result.Result;
import com.atguigu.service.CommunityService;
import com.atguigu.service.DictService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/community")
public class CommunityControlley extends BaseController{

    @Reference
    private  CommunityService communityService;

    @Reference
    private DictService dictService;

    @RequestMapping
    public String index(Map map, HttpServletRequest request){
        String beijing = "beijing";
        Map<String, Object> filters = getFilters(request);
        List<Dict> areaList = dictService.findListByDictCode(beijing);
        map.put("areaList",areaList);
        PageInfo<Community> page = communityService.findPage(filters);
        map.put("page",page);
        map.put("filters",filters);
        return "community/index";
    }

    @RequestMapping("/create")
    public String toCreate(Map map){
        String beijing = "beijing";
        List<Dict> areaList = dictService.findListByDictCode(beijing);
        map.put("areaList",areaList);
        return "community/create";
    }
    @RequestMapping("/save")
    public String save(Community community){
        communityService.insert(community);
        return "common/successPage";
    }

    @RequestMapping("/edit/{id}")
    public String toEdit(@PathVariable("id") Long id,Map map){
        String beijing = "beijing";
        List<Dict> areaList = dictService.findListByDictCode(beijing);
        map.put("areaList",areaList);
        Community community = communityService.getById(id);
        map.put("community",community);
        return "community/edit";

    }

    @RequestMapping("/update")
    public String update(Community community){
        communityService.update(community);
        return "common/successPage";
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id ){
        communityService.delete(id);
        return "redirect:/community";
    }
}
