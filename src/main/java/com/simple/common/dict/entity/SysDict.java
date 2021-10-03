package com.simple.common.dict.entity;

/**
 * 字典信息实体类
 * @author Simple
 * @since 2021-10-03
 */
public class SysDict {

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 字典名称
     */
    private String typeName;

    /**
     * 字典编码
     */
    private String typeCode;

    /**
     * 对照（代码）
     */
    private String code;

    /**
     * 对照（值）
     */
    private String meaning;

    /**
     * 键值对序列号
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
    public static final String COL_TYPE_NAME = "type_name";
    public static final String COL_TYPE_CODE = "type_code";
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
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
                ", typeName='" + typeName + '\'' +
                ", typeCode='" + typeCode + '\'' +
                ", code='" + code + '\'' +
                ", meaning='" + meaning + '\'' +
                ", seqNum=" + seqNum +
                ", expand='" + expand + '\'' +
                '}';
    }
}
