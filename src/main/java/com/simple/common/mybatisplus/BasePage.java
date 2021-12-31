package com.simple.common.mybatisplus;

/**
 * 分页查询基本条件，分页查询Vo的父类
 * @author Simple 2021/12/2
 */
public class BasePage {

    /**
     * 排序规则：降序
     */
    public static final String SORT_DESC = "desc";
    /**
     * 排序规则：升序
     */
    public static final String SORT_ASC = "asc";
    /**
     * 每页显示条数，默认 10
     */
    private long size = 10;
    /**
     * 当前页，默认 1
     */
    private long current = 1;
    /**
     * 排序字段 示例：orderBy=age:asc&orderBy=name:desc
     */
    private String[] orderBy;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public String[] getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String[] orderBy) {
        this.orderBy = orderBy;
    }
}
