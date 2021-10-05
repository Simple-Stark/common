package com.simple.common.dict.entity;

/**
 * 字典输出Vo
 * @author Simple
 * @date 2021/10/4 14:59
 */
public class SysDictVo {
    /**
     * 字典映射（key）
     */
    private String code;
    /**
     * 字典映射（value）
     */
    private String meaning;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
