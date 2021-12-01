package com.simple.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Mybatis-Plus 配置类
 * @author Simple 2021/11/23
 */
@Configuration
public class MybatisPlusConfig {

    private final Logger log = LoggerFactory.getLogger(MybatisPlusConfig.class);

    @Resource
    private DataSource dataSource;

    /**
     * 【Bean】Mybatis Plus 插件拦截器列表
     * @return com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
     * @throws SQLException Sql 连接异常
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() throws SQLException {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 获取数据库类型
        DbType dbType = getDbType();
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(dbType));
        log.info("Mybatis-Plus 插件拦截器加载完成{}，分页插件数据库类型为：{}",interceptor.getInterceptors(),dbType.getDesc());
        return interceptor;
    }

    /**
     * 【工具】根据Databases Url获取数据库类型
     * @return MybatisPlus 支持的数据库类型
     * @throws SQLException Sql 连接异常
     */
    private DbType getDbType() throws SQLException {
        Connection connection = dataSource.getConnection();
        String url = connection.getMetaData().getURL();
        String db = url.split(":")[1];
        return DbType.getDbType(db);
    }

}
