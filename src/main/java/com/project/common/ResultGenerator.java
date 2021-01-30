package com.project.common;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {

    public static Result genSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(Constants.DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Result<T> genSuccessResult(T data) {
        return new Result<T>()
                .setCode(ResultCode.SUCCESS)
                .setMessage(Constants.DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }

    public static Result genServerErrorResult() {
        return new Result()
                .setCode(ResultCode.INTERNAL_SERVER_ERROR)
                .setMessage(Constants.SYSTEM_ERROR);
    }

    public static Result genUnAuthResult(String message) {
        return new Result()
                .setCode(ResultCode.UNAUTHORIZED)
                .setMessage(message);
    }

    public static Result genServiceErrorResult() {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(Constants.SERVICE_ERROR);
    }

    public static Result genUrlNotFoundResult() {
        return new Result()
                .setCode(ResultCode.NOT_FOUND)
                .setMessage(Constants.URL_NOT_FOUND);
    }
}
