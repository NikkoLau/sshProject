package com.atguigu.mapper;

import com.atguigu.entity.Dict;

import java.util.List;
import java.util.Map;

public interface DictMapper {
     List<Dict> findListByparentId(Long id);

    Integer isParent(Long id);


    Dict getDictByDictCode(String dictCode);

    String getNameById(Long id);

}
