package com.simple.common.mybatisplus;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.File;
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
                // 数据库时间类型到实体类时间类型对应策略
                .dateType(DateType.TIME_PACK)
                .build();

        // 自定义文件输出位置
        Map<OutputFile, String> pathInfo = new HashMap<>(2);
        pathInfo.put(OutputFile.mapperXml,xmlSrcDir);
        // vo、query等文件
        pathInfo.put(OutputFile.other,joinPath(outPutDir,packName));

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
                // 实体继承父类配置
                .superClass(BaseEntity.class)
                // 生成字段常量
                .enableColumnConstant()
                // 主键生成策略
                .idType(IdType.ASSIGN_ID)
                // 开启 lombok 模型
                .enableLombok()
                .build();

        // 模板配置
        TemplateConfig templateConfig = new TemplateConfig.Builder()
                .entity("/templates/SimpleEntity.java")
                .service("/templates/SimpleService.java")
                .serviceImpl("/templates/SimpleServiceImpl.java")
                .mapper("/templates/SimpleMapper.java")
                .mapperXml("/templates/SimpleMapper.xml")
                .controller("/templates/SimpleController.java")
                .build();

        // 设置自定义注入属性
        // 自定义文件
        Map<String, String> customFile = new HashMap<>(1);
        customFile.put("PageQuery.java", "/templates/SimplePageQuery.java.ftl");
        // 自定义属性
        Map<String, Object> customMap = new HashMap<>();
        customMap.put("packageQuery", packName + ".vo.query");
        InjectionConfig injectionConfig = new InjectionConfig.Builder()
                .customFile(customFile)
                .customMap(customMap)
                .build();


        // 设置策略并生成代码
        generator.global(globalConfig)
                // 模板配置
                .template(templateConfig)
                // 包配置
                .packageInfo(packageConfig)
                // 策略配置
                .strategy(strategyConfig)
                // 注入配置
                .injection(injectionConfig)
                // 使用自定义引擎模板，重写其它文件输出方法生成Vo等文件
                .execute(new SimpleFreemarkerEngine());
    }

    /**
     * 【工具】将包名转换为文件夹路径与输出路径拼接
     *
     * @param parentDir   路径常量字符串
     * @param packageName 包名
     * @return 拼接后的路径
     */
    private String joinPath(String parentDir, String packageName) {
        if (StringUtils.isBlank(parentDir)) {
            parentDir = javaSrcDir;
        }
        if (!StringUtils.endsWith(parentDir, File.separator)) {
            parentDir += File.separator;
        }
        packageName = packageName.replaceAll("\\.", StringPool.BACK_SLASH + File.separator);
        return parentDir + packageName;
    }
}
