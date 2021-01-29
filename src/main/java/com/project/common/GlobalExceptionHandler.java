package com.project.common;

import com.project.core.Result;
import com.project.core.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public Result AuthorizationHandler() {
        log.error("没有通过权限验证！");
        return ResultGenerator.genFailResult("没有通过权限验证！");
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result ExceptionHandler(Exception e){
        log.error("系统内部异常",e);
        return ResultGenerator.genServerErrorResult();
    }
}