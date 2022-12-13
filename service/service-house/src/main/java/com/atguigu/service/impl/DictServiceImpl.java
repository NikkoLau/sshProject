package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.entity.Dict;
import com.atguigu.mapper.DictMapper;
import com.atguigu.result.Result;
import com.atguigu.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = DictService.class)
@Transactional
public class DictServiceImpl implements DictService {

    @Autowired
    private DictMapper dictMapper;
    @Override
    public List<Map<String, Object>> findZnodes(Long id) {
        // 返回数据[{ id:2, isParent:true, name:"随意勾选 2"}]
        //根据id获取子节点数据
        List<Dict> dicts = dictMapper.findListByparentId(id);
        List<Map<String,Object>> list = new ArrayList<>();
        for (Dict dict : dicts) {
            HashMap<String ,Object> hashMap = new HashMap();
            hashMap.put("id",dict.getId());
            //判断该节点是否是父节点
            Integer parent = dictMapper.isParent(dict.getId());
            hashMap.put("isParent",parent>0? true :false);
            hashMap.put("name",dict.getName());
            list.add(hashMap);
        }
        //全部权限列表
        return list;
    }

    @Override
    public List<Dict> findListByDictCode(String dictCode) {
        Dict dict = dictMapper.getDictByDictCode(dictCode);
        if (dict ==null)
            return null;
        List<Dict> list = dictMapper.findListByparentId(dict.getId());
        return list;
    }

    @Override
    public List<Dict> findListByparentId(Long parentId) {
        return dictMapper.findListByparentId(parentId);
    }

    @Override
    public String getNameById(Long id) {
        return dictMapper.getNameById(id);
    }

}
