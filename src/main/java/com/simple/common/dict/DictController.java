package com.simple.common.dict;

import com.simple.common.dict.entity.SysDictVo;
import com.simple.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典控制层
 * @author Simple
 * @date 2021/10/4 11:14
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    @Resource
    DictCache dictCache;

    /**
     * 【查询】 根据指定编码查询该编码下的所有字典列表
     * @param dictCode 字典编码
     * @return 指定dictCode 下的SysDictVo 列表
     */
    @GetMapping("/getDictList/{dictCode}")
    public Result<List<SysDictVo>> getDictByDictCode(@PathVariable String dictCode) {
        return Result.success(dictCache.getDictByDictCode(dictCode));
    }
}
