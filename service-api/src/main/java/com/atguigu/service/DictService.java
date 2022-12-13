package com.atguigu.service;

import com.atguigu.entity.Dict;

import java.util.List;
import java.util.Map;

public interface DictService {
    /**
     * 根据上级id获取子节点数据列表
     * @param id
     * @return
     */
    List<Map<String,Object>> findZnodes(Long id);
    /**
     * 根据编码获取子节点数据列表
     * @param dictCode
     * @return
     */
    List<Dict> findListByDictCode(String dictCode);

    List<Dict> findListByparentId(Long parentId);

    String getNameById(Long id);

}
