package com.simple.common.mybatisplus;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类
 * @author Simple 2022/1/20
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 删除标识：0：正常；1：已删除
     */
    @TableLogic
    private String delFlag;

    /**
     * 乐观锁
     */
    @Version
    private String version;

    /**
     *  数据库列名常量
     */
    public static final String COL_CREATE_USER = "create_user";
    public static final String COL_CREATE_TIME = "create_time";
    public static final String COL_UPDATE_USER = "update_user";
    public static final String COL_UPDATE_TIME = "update_time";
    public static final String COL_DEL_FLAG = "del_flag";
    public static final String COL_VERSION = "version";
    /**
     *  实体属性常量
     */
    public static final String FIELD_CREATE_USER = "createUser";
    public static final String FIELD_CREATE_TIME = "createTime";
    public static final String FIELD_UPDATE_USER = "updateUser";
    public static final String FIELD_UPDATE_TIME = "updateTime";
    public static final String FIELD_DEL_FLAG = "delFlag";
    public static final String FIELD_VERSION = "version";


    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
