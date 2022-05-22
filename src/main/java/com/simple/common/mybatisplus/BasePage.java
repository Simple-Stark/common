package com.simple.common.mybatisplus;

import java.io.Serializable;

/**
 * 分页查询基本条件，分页查询Vo的父类
 * @author Simple 2021/12/2
 */
public class BasePage implements Serializable {

    private static final long serialVersionUID = 150216828564444662L;

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
     * 排序字段,可传多个 示例：orderBy=age:asc
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
