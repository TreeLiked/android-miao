package com.ywdnf.androidmiao.config;

import com.ywdnf.androidmiao.utils.OsUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lqs2
 * @description TODO
 * @date 2018/9/23, Sun
 */
@Configuration
public class FileConfiguration implements WebMvcConfigurer {


    @Value("${file.image.path}")
    private String imgPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (OsUtils.isLinux()) {
            imgPath = "file:/etc/miao/images/";
        }
        registry.addResourceHandler("/file/img/**").addResourceLocations(imgPath);
    }
}
