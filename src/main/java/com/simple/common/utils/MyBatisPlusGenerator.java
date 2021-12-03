package com.simple.common.utils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MyBatisPlus 代码自动生成器
 *
 * @author Simple 2021/9/26
 */
@Component
public class MyBatisPlusGenerator {

    /**
     * 数据库配置
     */
    @Resource
    private DataSource dataSource;

    /**
     * 项目路径
     */
    static String projectDir = System.getProperty("user.dir");
    /**
     * 普通文件生成路径
     */
    static String javaSrcDir = projectDir + "/src/main/java";
    /**
     * mapper.xml文件生成路径
     */
    static String xmlSrcDir = projectDir + "/src/main/resources/mapper";

    /**
     * 【工具重载】 自动生成代码
     * @param tableList 需要生成代码的数据库表
     * @param author 作者
     * @param packName 父包名
     */
    public void generator(List<String> tableList,String author,String packName) {
        generator(tableList,author,packName,javaSrcDir,"");
    }

    /**
     * 【工具重载】 自动生成代码
     * @param tableList 需要生成代码的数据库表
     * @param author 作者
     * @param packName 父包名
     * @param outPutDir 指定输出路径。默认 javaSrcDir
     */
    public void generator(List<String> tableList,String author,String packName, String outPutDir) {
        generator(tableList,author,packName,outPutDir,"");
    }

    /**
     * 【工具重载】 代码自动生成
     * @param tableList 需要生成代码的数据库表
     * @param author 作者
     * @param packName 父包名
     * @param outPutDir 指定输出路径。默认 javaSrcDir
     * @param moduleName 模块名
     */
    public void generator(List<String> tableList,String author, String packName, String outPutDir, String moduleName) {

        // 数据库配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(dataSource).build();
        // 创建生成器
        AutoGenerator generator = new AutoGenerator(dataSourceConfig);
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig.Builder()
                // 作者
                .author(author)
                // 文件输出目录
                .outputDir(outPutDir)
                // 数据库时间类型 到 实体类时间类型 对应策略，此处为”只使用 java.util.date 代替“
                .dateType(DateType.ONLY_DATE)
                .build();

        // 自定义输出位置，主要用于mapper.xml
        Map<OutputFile, String> pathInfo = new HashMap<>(16);
        pathInfo.put(OutputFile.mapperXml,xmlSrcDir);

        // 包配置
        PackageConfig packageConfig = new PackageConfig.Builder()
                // 父包名
                .parent(packName)
                // 模块名
                .moduleName(moduleName)
                // 自定义文件输出位置
                .pathInfo(pathInfo)
                .build();

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                // 跳过视图
                .enableSkipView()
                // 想要自动生成代码的表
                .addInclude(tableList.toArray(new String[0]))
                // controller 策略配置
                .controllerBuilder()
                // 生成@RestController 风格
                .enableRestStyle()
                // 实体策略配置
                .entityBuilder()
                // 生成字段常量
                .enableColumnConstant()
                // 主键生成策略
                .idType(IdType.ASSIGN_ID)
                // 开启 lombok 模型
                .enableLombok()
                .build();

        // 模板配置
        TemplateConfig templateConfig = new TemplateConfig.Builder()
                .entity("/templates/entity.java")
                // .service("/templates/service.java")
                // .serviceImpl("/templates/serviceImpl.java")
                // .mapper("/templates/mapper.java")
                // .mapperXml("/templates/mapper.xml")
                // .controller("/templates/controller.java")
                .build();

        // 设置策略并生成代码
        generator.global(globalConfig)
                .packageInfo(packageConfig)
                .strategy(strategyConfig)
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute(new FreemarkerTemplateEngine());
    }
}
