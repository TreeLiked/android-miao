package com.ywdnf.androidmiao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lqs2
 * @description test connection
 * @date 2018/9/6, Thu
 */
@RestController
public class TestController {

    @RequestMapping(value = "test")
    public String test() {

        return "Successfully connect to the server.";
    }



}
