package com.simple.common.dict;

import com.simple.common.dict.entity.SingleDict;
import com.simple.common.dict.entity.SysDict;
import com.simple.common.dict.entity.SysDictVo;
import com.simple.common.dict.service.SysDictServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 字典缓存容器
 * @author Simple
 * @date 2021/10/4 14:25
 */
public class DictCache {

    private final Logger log = LoggerFactory.getLogger(DictCache.class);

    /**
     * 字典缓存Map 容器
     */
    private Map<String, SingleDict> dictMap;

    /**
     * 【构造器】 默认创建方式，根据字典编码服务实现类创建
     * @param sysDictService 字典编码服务实现类
     */
    public DictCache(SysDictServiceImpl sysDictService) {
        refreshCache(sysDictService);
    }

    /**
     * 【更新】 根据字典编码服务实现类刷新缓存容器内容
     * @param sysDictService 字典编码服务实现
     */
    public void refreshCache(SysDictServiceImpl sysDictService) {
        // 获取所有字典信息
        List<SysDict> list = sysDictService.list();
        // 根据dictCode 进行分组
        Map<String, List<SysDict>> map = list.stream().collect(Collectors.groupingBy(SysDict::getDictCode));
        // 创建缓存容器
        Map<String, SingleDict> dictMap = new ConcurrentHashMap<>(map.size());
        map.forEach((k,v) -> {
            SingleDict singleDict = new SingleDict(v);
            dictMap.put(k,singleDict);
        });
        this.dictMap = dictMap;
        log.info("字典缓存容器更新完成：{}",dictMap);
    }

    /**
     * 【查询】 根据指定字典编码和映射键code 获取具体映射值 meaning
     * @param dictCode 字典编码
     * @param code 映射code
     * @return 具体映射meaning
     */
    public String getMeaning(String dictCode, String code) {
        return this.dictMap.get(dictCode).getDictMap().get(code).getMeaning();
    }

    /**
     * 【查询】 根据指定编码查询该编码下的所有字典列表
     * @param dictCode 字典编码
     * @return 指定dictCode 下的SysDictVo 列表
     */
    public List<SysDictVo> getDictByDictCode(String dictCode) {
        List<SysDict> dictList = this.dictMap.get(dictCode).getDictList();
        return  dictList.stream().map(x -> {
            SysDictVo vo = new SysDictVo();
            BeanUtils.copyProperties(x,vo);
            return vo;
        }).collect(Collectors.toList());
    }
}
