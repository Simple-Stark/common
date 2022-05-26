package com.simple.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.simple.common.mybatisplus.BaseEntity;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Mybatis-plus 自动填充插件
 * @author Simple 2022/5/26
 */
@Component
public class SimpleMetaObjectHandler implements MetaObjectHandler {

    private final Logger log = LoggerFactory.getLogger(MetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        log.debug("插入填充 ....");
        // TODO 创建人、修改人
        // 创建时间
        this.strictInsertFill(metaObject, BaseEntity.FIELD_CREATE_TIME, LocalDateTime.class, LocalDateTime.now());
        // 修改时间
        this.strictInsertFill(metaObject, BaseEntity.FIELD_UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
        // 删除标识
        this.strictInsertFill(metaObject, BaseEntity.FIELD_DEL_FLAG, String.class, "0");
        // 乐观锁
        this.strictInsertFill(metaObject, BaseEntity.FIELD_VERSION, Long.class, 0L);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("更新填充 ....");
        // TODO 修改人
        // 修改时间
        this.strictUpdateFill(metaObject, BaseEntity.FIELD_UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
    }
}
