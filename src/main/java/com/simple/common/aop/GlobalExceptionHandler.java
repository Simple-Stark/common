package com.simple.common.aop;

import com.simple.common.exception.SimpleException;
import com.simple.common.result.CodeMsg;
import com.simple.common.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * 全局异常拦截器
 *
 * @author Simple 2021/9/23
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 通用异常处理方法
     * @param e 未知异常
     * @return 异常信息
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Void> error(Exception e) {
        log.error("未特定捕捉的异常，Exception =====>> {}",e.getMessage(),e);
        return Result.error(CodeMsg.BASE_ERROR.fillArgs(e.getMessage()));
    }

    /**
     * 前端传参数校验BindException 处理
     * 对象以表单形式传输时触发
     * @param e 参数校验异常
     * @return 校验信息提示
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result<Void> error(BindException e) {
        // 打印日志
        log.error("前端传参校验异常，BindExceptionHandler =====>> {}",e.getMessage(),e);
        List<ObjectError> errors = e.getAllErrors();
        ObjectError error = errors.get(0);
        String msg = error.getDefaultMessage();
        return Result.error(CodeMsg.BASE_BIND_ERROR.fillArgs(msg));
    }

    /**
     * 前端传参数校验ConstraintViolationException 处理
     * 非实体对象传输，绑定单个
     * @param e 参数校验异常
     * @return 校验信息提示
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result<Void> error(ConstraintViolationException e) {
        // 打印日志
        log.error("前端传参校验异常，ConstraintViolationException =====>> {}",e.getMessage(),e);
        String msg = e.getMessage().split(":")[1];
        return Result.error(CodeMsg.BASE_BIND_ERROR.fillArgs(msg));
    }

    /**
     * 前端传参数校验MethodArgumentNotValidException 处理
     * 对象以Json形式传输
     * @param e 参数校验异常
     * @return 校验信息提示
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<Void> error(MethodArgumentNotValidException e) {
        // 打印日志
        log.error("前端传参校验异常，MethodArgumentNotValidException =====>> {}",e.getMessage(),e);
        String msg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
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
        log.error("自定义异常，SimpleExceptionHandler =====>> {}",e.getMessage(),e);
        return Result.error(e.getCodeMsg());
    }

    /**
     * 业务非法参数校验异常处理方法（Spring 的 Assert）
     * @param e 参数校验异常
     * @return com.wrh.basis.common.Result 实例
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Result<Void> error(IllegalArgumentException e) {
        // 打印日志
        log.error("业务非法参数校验，IllegalArgumentExceptionHandler =====>> {}",e.getMessage(),e);
        return Result.error(CodeMsg.BASE_BUSINESS_ERROR.fillArgs(e.getMessage()));
    }
}
