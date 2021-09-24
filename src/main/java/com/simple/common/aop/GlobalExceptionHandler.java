package com.simple.common.aop;

import com.simple.common.exception.SimpleException;
import com.simple.common.result.CodeMsg;
import com.simple.common.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 全局异常拦截器
 *
 * @author Simple
 * @date 2021/9/23 19:48
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private  final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 通用异常处理方法
     * @param e 未知异常
     * @return com.wrh.basis.common.Result 实例
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Void> error(Exception e) {
        log.error("未特定捕捉Exception =====>> {}",e.getMessage(),e);
        return Result.error(CodeMsg.BASE_ERROR.fillArgs(e.getMessage()));
    }

    /**
     * 参数校验处理方法
     * @param e 参数校验异常
     * @return com.wrh.basis.common.Result 实例
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result<Void> error(BindException e) {
        // 打印日志
        log.error("BindExceptionHandler =====>> {}",e.getMessage(),e);
        List<ObjectError> errors = e.getAllErrors();
        ObjectError error = errors.get(0);
        String msg = error.getDefaultMessage();
        return Result.error(CodeMsg.BASE_BIND_ERROR.fillArgs(msg));
    }

    /**
     * 自定义测试异常处理方法
     * @param e 自定义测试异常类
     * @return com.wrh.basis.common.Result 实例
     */
    @ExceptionHandler(SimpleException.class)
    @ResponseBody
    public Result<Void> error(SimpleException e) {
        // 打印日志
        log.error("SimpleExceptionHandler =====>> {}",e.getMessage(),e);
        return Result.error(e.getResultCode());
    }
}
