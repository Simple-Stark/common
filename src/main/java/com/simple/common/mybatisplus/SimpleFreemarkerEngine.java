package com.simple.common.mybatisplus;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.simple.common.exception.SimpleException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * 自定义模板引擎（基于FreemarkerTemplateEngine）
 * @author Simple 2022/1/11
 */
public class SimpleFreemarkerEngine extends AbstractTemplateEngine {
    private Configuration configuration;

    @Override
    public @NotNull SimpleFreemarkerEngine init(@NotNull ConfigBuilder configBuilder) {
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding(ConstVal.UTF8);
        configuration.setClassForTemplateLoading(FreemarkerTemplateEngine.class, StringPool.SLASH);
        return this;
    }


    @Override
    public void writer(@NotNull Map<String, Object> objectMap, @NotNull String templatePath, @NotNull File outputFile) throws Exception {
        Template template = configuration.getTemplate(templatePath);
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            template.process(objectMap, new OutputStreamWriter(fileOutputStream, ConstVal.UTF8));
        }
    }

    @Override
    public @NotNull String templateFilePath(@NotNull String filePath) {
        return filePath + ".ftl";
    }

    /**
     * 【自定义】输出自定义模板文件
     *
     * @param customFile 自定义配置模板文件信息
     * @param tableInfo  表信息
     * @param objectMap  渲染数据
     * @author Simple 2022/1/11
     */
    protected void outputCustomFile(@NotNull Map<String, String> customFile, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        String otherPath = getPathInfo(OutputFile.other);
        customFile.forEach((key, value) -> {
            // 根据自定义模板文件类型确定包路径
            String filePath;
            if (key.contains("Query")) {
                // 查询
                filePath = "query";
            } else if (key.contains("Result")) {
                // 返回结果
                filePath = "dto";
            } else {
                // VO
                filePath = "vo";
            }
            String fileName = String.format((otherPath + File.separator + "vo" + File.separator + filePath + File.separator + entityName + "%s"), key);
            outputFile(new File(fileName), objectMap, value);
        });
    }

}
