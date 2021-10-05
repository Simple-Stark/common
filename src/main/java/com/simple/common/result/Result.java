package com.simple.common.result;

/**
 * 全局消息统一返回
 *
 * @author Simple 2021/9/21
 */
public class Result<T> {

    /**
     * 全局消息统一返回 响应码
     */
    private final Integer code;
    /**
     * 全局消息统一返回 响应消息
     */
    private final String msg;
    /**
     * 全局消息统一返回 响应数据
     */
    private final T data;

    /**
     * 构造器私有，防止直接创建
     */
    private Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 【构造器】私有构造器
     * @param codeMsg 全局统一消息返回
     * @param data 返回数据
     */
    private Result(CodeMsg codeMsg, T data) {
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
        this.data = data;
    }

    public static Result<Void> success() {
        return new Result<>(CodeMsg.BASE_SUCCESS, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(CodeMsg.BASE_SUCCESS, data);
    }

    public static Result<Void> error() {
        return new Result<>(CodeMsg.BASE_ERROR, null);
    }

    public static <T> Result<T> error(T data) {
        return new Result<>(CodeMsg.BASE_ERROR, data);
    }

    public static Result<Void> error(CodeMsg codeMsg) {
        return new Result<>(codeMsg, null);
    }

    public static Result<Void> error(String msg) {
        return new Result<>(CodeMsg.BASE_ERROR.getCode(),msg, null);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
