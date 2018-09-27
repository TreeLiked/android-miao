package com.ywdnf.androidmiao.global;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lqs2
 * @description 全局异常拦截
 * @date 2018/8/3, Fri
 */
@Configuration
@ControllerAdvice(basePackages = {"com.ywdnf.androidmiao.controller", "com.ywdnf.androidmiao.service"})
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String errorCatch() {
        return "error";
    }
}
