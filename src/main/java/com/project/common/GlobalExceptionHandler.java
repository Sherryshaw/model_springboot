package com.project.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public Result AuthorizationHandler() {
        log.info("权限不足！");
        return ResultGenerator.genUnAuthResult("权限不足！");
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public Result AuthenticationHandler() {
        log.info("未经认证！");
        return ResultGenerator.genUnAuthResult("未经认证！");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public Result NoHandlerFoundException() {
        log.info(Constants.URL_NOT_FOUND);
        return ResultGenerator.genUrlNotFoundResult();
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result ServiceHandler(ServiceException e) {
        log.error("业务逻辑异常", e);
        return ResultGenerator.genServiceErrorResult();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result ExceptionHandler(Exception e) {
        log.error("系统内部异常", e);
        return ResultGenerator.genServerErrorResult();
    }
}