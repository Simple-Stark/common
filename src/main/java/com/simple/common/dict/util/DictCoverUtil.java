package com.simple.common.dict.util;

import com.simple.common.dict.DictCache;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 字典转换工具类
 * @author Simple 2021/10/3
 */
public class DictCoverUtil {

    private final Logger log = LoggerFactory.getLogger(DictCoverUtil.class);

    /**
     * 缓存容器
     */
    private final DictCache dictCache;

    /**
     * 【构造器】使用指定缓存容器创建一个字典转换工具类
     * @param dictCache 缓存容器
     */
    public DictCoverUtil(DictCache dictCache) {
        this.dictCache = dictCache;
    }

    /**
     * 【工具】 按照注解 转换指定对象的字典编码
     * @param object 需要转换的对象
     * @param <T> 对象类型
     */
    public <T> void coverCodeToMean(T object) {
        // 判空
        if (null == object) {
            return;
        }
        // 获取类属性，转Map（key：属性名，value：属性对象）
        Map<String, Field> fieldMap = getObjectFieldMap(object);

        // 遍历循环处理数据
        for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
            Field field = entry.getValue();
            if (field.isAnnotationPresent(DictPint.class)) {
                // 获取对应的字典编码
                String dictCode = field.getAnnotation(DictPint.class).dictCode();
                if (StringUtils.isNotBlank(dictCode)) {
                    // 字典不为空，需要进行转换
                    try {
                        field.setAccessible(true);
                        // 获取当前属性对应的值
                        String code = field.get(object) == null ? null : field.get(object).toString();
                        if (StringUtils.isNotBlank(code)) {
                            // 值不为空，进行转换
                            field.setAccessible(true);
                            String meaning = dictCache.getMeaning(dictCode,code);
                            // 获取前置属性
                            String beforeValueTo = field.getAnnotation(DictPint.class).beforeValueTo();
                            if (StringUtils.isNotBlank(beforeValueTo)) {
                                // 获取前置属性的Field,并将code 放到该属性
                                Field beforeField = fieldMap.get(beforeValueTo);
                                beforeField.setAccessible(true);
                                beforeField.set(object,code);
                            }
                            // 获取后置属性
                            String afterValueTo = field.getAnnotation(DictPint.class).afterValueTo();
                            if (StringUtils.isNotBlank(afterValueTo)) {
                                // 获取后置属性的Field,并将meaning 放到该属性
                                Field afterField = fieldMap.get(afterValueTo);
                                afterField.setAccessible(true);
                                afterField.set(object,meaning);
                            } else {
                                // 后置未传值，则不管前置是否传值，都将meaning 放到当前属性
                                field.set(object, meaning);
                            }
                        }
                    } catch (Exception e) {
                        log.warn("{}对象字典转换发生异常：{}",object.toString(),e.getMessage(),e);
                    }
                }
            }
        }
    }

    /**
     * 【工具，重载】 按照注解 转换指定对象列表的字典编码
     * @param list 需要转换的对象列表
     * @param <T> 对象类型
     */
    public <T> void coverCodeToMean(List<T> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            for (T t : list) {
                coverCodeToMean(t);
            }
        }
    }

    /**
     * 将对象的属性置入Map中，避免遍历
     *
     * @param object obj
     * @return Map(Obj Name - Obj)
     */
    private Map<String, Field> getObjectFieldMap(Object object) {
        // 获取所有属性
        Field[] fields = object.getClass().getDeclaredFields();
        if (fields.length > 0) {
            return Arrays.stream(fields).collect(Collectors.toMap(Field::getName, f -> f));
        } else {
            return new HashMap<>(0);
        }
    }
}
