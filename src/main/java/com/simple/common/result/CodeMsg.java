package com.simple.common.result;

/**
 * 全局统一消息与返回消息对应类
 *
 * @author Simple 2021/9/22
 */
public class CodeMsg {
    /**
     * 全局通用错误码
     */
    public static CodeMsg BASE_SUCCESS = new CodeMsg(200, "success");
    public static CodeMsg BASE_ERROR = new CodeMsg(100, "服务端异常：%s");
    public static CodeMsg BASE_BIND_ERROR = new CodeMsg(400, "参数校验异常：%s");
    public static CodeMsg BASE_BUSINESS_ERROR = new CodeMsg(500, "业务异常：%s");

    /**
     * 响应状态码
     */
    private final Integer code;
    /**
     * 响应信息
     */
    private final String msg;

    /**
     * 【构造器】protected修饰，支持子类继承扩展统一消息返回
     * @param code 自定义响应码
     * @param msg 自定义消息通知
     */
    protected CodeMsg(int code, String msg) {
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

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
