package com.simple.common.exception;

import com.simple.common.result.CodeMsg;

/**
 * 自定义异常
 *
 * @author Simple 2020-11-16
 */
public class SimpleException extends RuntimeException{
    /**
     * 异常错误信息
     */
    private final CodeMsg codeMsg;

    /**
     * 【构造器】基于CodeMsg.BASE_BUSINESS_ERROR 及异常提示信息创建业务异常
     * @param message 自定义异常提示信息
     */
    public SimpleException(String message) {
        super(message);
        this.codeMsg = CodeMsg.BASE_BUSINESS_ERROR.fillArgs(message);
    }

    /**
     * 【构造器】根据自定义CodeMsg 实例创建业务异常
     * @param codeMsg 自定义CodeMsg
     */
    public SimpleException(CodeMsg codeMsg) {
        super(codeMsg.getMsg());
        this.codeMsg = codeMsg;
    }

    public CodeMsg getResultCode() {
        return codeMsg;
    }

    @Override
    public String toString() {
        return "SimpleException{" + codeMsg.toString() + "}";
    }
}
