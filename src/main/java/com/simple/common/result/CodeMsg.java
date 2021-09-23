package com.simple.common.result;

import lombok.Data;

/**
 * 全局统一消息与返回消息对应类
 *
 * @author Simple
 * @date 2021/9/22 22:08
 */
@Data
public class CodeMsg {
    /**
     * 全局通用错误码
     */
    public static CodeMsg BASE_SUCCESS = new CodeMsg(200, "success");
    public static CodeMsg BASE_ERROR = new CodeMsg(100, "服务端异常");
    public static CodeMsg BASE_BIND_ERROR = new CodeMsg(400, "参数校验异常：%s");
    public static CodeMsg BASE_BUSINESS_ERROR = new CodeMsg(500, "业务异常：%s");

    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String msg;

    /**
     * 【构造器】私有构造器，防止直接创建
     * @param code 自定义响应码
     * @param msg 自定义消息通知
     */
    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 【工具方法】 补充指定错误码信息
     * @param args 补充信息
     * @return CodeMsg 实例
     */
    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }
}
