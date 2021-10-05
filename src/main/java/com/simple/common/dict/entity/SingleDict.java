package com.simple.common.dict.entity;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 单个dictCode 对应的字典
 * @author Simple 2021/10/4
 */
public class SingleDict {

    /**
     * 单个dictCode
     * code - SysDict 映射Map
     */
    private Map<String, SysDict> dictMap;

    /**
     * 单个dictCode 下
     * SysDict 列表
     */
    private List<SysDict> dictList;

    /**
     * 【构造方法】 创建单个dictCode 对应的字典实例
     * @param list 单个dictCode 对应的字典列表
     */
    public SingleDict(List<SysDict> list) {
        // 将排序后的字典放入dictList
        this.dictList = list.stream()
                .sorted(Comparator.comparing(SysDict::getSeqNum))
                .collect(Collectors.toList());
        // 生成映射Map 放入dictMap
        this.dictMap = list.stream().collect(Collectors.toMap(SysDict::getCode, s -> s));
    }

    public Map<String, SysDict> getDictMap() {
        return dictMap;
    }

    public void setDictMap(Map<String, SysDict> dictMap) {
        this.dictMap = dictMap;
    }

    public List<SysDict> getDictList() {
        return dictList;
    }

    public void setDictList(List<SysDict> dictList) {
        this.dictList = dictList;
    }

    @Override
    public String toString() {
        return "SingleDict{" +
                "dictMap=" + dictMap +
                ", dictList=" + dictList +
                '}';
    }
}
