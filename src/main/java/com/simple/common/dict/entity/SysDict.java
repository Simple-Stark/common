package com.simple.common.dict.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 字典信息实体类
 * @author Simple
 * @since 2021-10-03
 */
@TableName("sys_dict")
public class SysDict {

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 字典映射（key）
     */
    private String code;

    /**
     * 字典映射（value）
     */
    private String meaning;

    /**
     * 键值对序列号（排序用）
     */
    private Integer seqNum;

    /**
     * 扩展信息
     */
    private String expand;

    /**
     * 数据库表字段常量
     */
    public static final String COL_ID = "id";
    public static final String COL_DICT_NAME = "dict_name";
    public static final String COL_DICT_CODE = "dict_code";
    public static final String COL_CODE = "code";
    public static final String COL_MEANING = "meaning";
    public static final String COL_SEQ_NUM = "seq_num";
    public static final String COL_EXPAND = "expand";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

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

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    @Override
    public String toString() {
        return "SysDict{" +
                "id=" + id +
                ", dictName='" + dictName + '\'' +
                ", dictCode='" + dictCode + '\'' +
                ", code='" + code + '\'' +
                ", meaning='" + meaning + '\'' +
                ", seqNum=" + seqNum +
                ", expand='" + expand + '\'' +
                '}';
    }
}
