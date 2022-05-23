package com.simple.common.mybatisplus;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simple.common.exception.SimpleException;
import com.simple.common.result.CodeMsg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.simple.common.mybatisplus.BasePage.*;

/**
 * 自定义分页插件
 * @author Simple 2021/12/1
 */
public class SimplePage<T> implements IPage<T> {

    private static final long serialVersionUID = 1L;
    /**
     * 查询数据列表
     */
    protected List<T> records = Collections.emptyList();

    /**
     * 总数
     */
    protected long total = 0;
    /**
     * 每页显示条数，默认 10
     */
    protected long size = 10;

    /**
     * 当前页
     */
    protected long current = 1;

    /**
     * 排序字段信息
     */
    @JsonIgnore
    protected List<OrderItem> orders = new ArrayList<>();

    /**
     * 是否自动优化 COUNT SQL
     */
    @JsonIgnore
    protected boolean optimizeCountSql = true;

    /**
     * 是否进行 count 查询
     */
    @JsonIgnore
    protected boolean searchCount = true;

    /**
     * 是否命中count缓存
     */
    @JsonIgnore
    protected boolean hitCount = false;

    /**
     * 【构造器】无参构造
     * @author Simple
     */
    public SimplePage() {
    }

    /**
     * 【构造器】有参构造
     *
     * @param current 当前页
     * @param size    每页显示条数
     * @author Simple
     */
    public SimplePage(long current, long size) {
        this(current, size, 0);
    }

    /**
     * 【构造器】根据BasePag创建SimplePage
     * @param page 基础分页条件
     */
    public SimplePage(BasePage page) {
        this(page.getCurrent(), page.getSize());
        // 排序判空
        if (page.getOrderBy() == null) {
            return;
        }
        for (String s : page.getOrderBy()) {
            String[] orderBy = s.split(":");
            String sort = orderBy[1].toLowerCase();
            switch (sort){
                case SORT_DESC:
                    this.addOrder(OrderItem.desc(orderBy[0]));
                    break;
                case SORT_ASC:
                    this.addOrder(OrderItem.asc(orderBy[0]));
                    break;
                default:
                    throw new SimpleException(CodeMsg.BASE_PAGE_SORT_ERROR.fillArgs("排序规则错误！"));
            }
        }
    }

    public SimplePage(long current, long size, long total) {
        this(current, size, total, true);
    }

    public SimplePage(long current, long size, boolean searchCount) {
        this(current, size, 0, searchCount);
    }

    public SimplePage(long current, long size, long total, boolean searchCount) {
        if (current > 1) {
            this.current = current;
        }
        this.size = size;
        this.total = total;
        this.searchCount = searchCount;
    }

    /**
     * 是否存在上一页
     *
     * @return true / false
     */
    public boolean hasPrevious() {
        return this.current > 1;
    }

    /**
     * 是否存在下一页
     *
     * @return true / false
     */
    public boolean hasNext() {
        return this.current < this.getPages();
    }

    /**
     * 添加新的排序条件，构造条件可以使用工厂 OrderItem
     *
     * @param items 条件
     * @return 返回分页参数本身
     */
    public SimplePage<T> addOrder(OrderItem... items) {
        orders.addAll(Arrays.asList(items));
        return this;
    }

    /**
     * 添加新的排序条件，构造条件可以使用工厂 OrderItem
     *
     * @param items 条件
     * @return 返回分页参数本身
     */
    public SimplePage<T> addOrder(List<OrderItem> items) {
        orders.addAll(items);
        return this;
    }

    @Override
    public List<OrderItem> orders() {
        return getOrders();
    }

    @Override
    public boolean optimizeCountSql() {
        return optimizeCountSql;
    }

    @Override
    public long getTotal() {
        return this.total;
    }

    @Override
    public SimplePage<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public SimplePage<T> setSize(long size) {
        this.size = size;
        return this;
    }

    @Override
    public long getCurrent() {
        return this.current;
    }

    @Override
    public SimplePage<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

    @Override
    public boolean isSearchCount() {
        if (total < 0) {
            return false;
        }
        return searchCount;
    }

    @Override
    public List<T> getRecords() {
        return this.records;
    }

    @Override
    public SimplePage<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    public List<OrderItem> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItem> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "SimplePage{" +
                "total=" + total +
                ", size=" + size +
                ", current=" + current +
                ", orders=" + orders +
                ", optimizeCountSql=" + optimizeCountSql +
                ", searchCount=" + searchCount +
                ", hitCount=" + hitCount +
                '}';
    }
}
