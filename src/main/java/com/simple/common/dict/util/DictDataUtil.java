package com.simple.common.dict.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 字典编码数据处理工具类
 * @author Simple
 * @date 2021/10/3 20:25
 */
public class DictDataUtil {

    /**
     * 将对象的属性置入Map中，避免遍历
     *
     * @param object obj
     * @return Map(Obj Name - Obj)
     */
    public static Map<String, Field> getObjectFieldMap(Object object) {
        // 获取所有属性
        Field[] fields = object.getClass().getDeclaredFields();
        if (fields.length > 0) {
            return Arrays.stream(fields).collect(Collectors.toMap(Field::getName, f -> f));
        } else {
            return new HashMap<>(0);
        }
    }
}
