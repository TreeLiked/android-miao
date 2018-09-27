package com.ywdnf.androidmiao;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author lqs2
 * @Description android app miao
 * @Date 2018/9/6
 **/
@SpringBootApplication
@EnableEurekaClient

public class AndroidMiaoApplication {


    public static void main(String[] args) {
        SpringApplication miao = new SpringApplication(AndroidMiaoApplication.class);
        miao.setBannerMode(Banner.Mode.OFF);
        miao.run(args);

    }
}
