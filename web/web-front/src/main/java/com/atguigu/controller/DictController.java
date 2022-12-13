package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Dict;
import com.atguigu.result.Result;
import com.atguigu.service.DictService;
import com.atguigu.vo.HouseVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dict")
public class DictController {
    
    @Reference
    private DictService dictService;

    @RequestMapping("/findListByDictCode/{dictCode}")
    public Result findListByDictCode(@PathVariable("dictCode") String dictCode){
        List<Dict> list = dictService.findListByDictCode(dictCode);
        return Result.ok(list);

    }

    @RequestMapping("/findListByParentId/{id}")
    public Result findListByParentId(@PathVariable("id")Long id){
        List<Dict> list = dictService.findListByparentId(id);
        return Result.ok(list);
    }
}
