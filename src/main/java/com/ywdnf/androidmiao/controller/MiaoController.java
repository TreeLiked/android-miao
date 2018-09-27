package com.ywdnf.androidmiao.controller;

import com.ywdnf.androidmiao.utils.OsUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author lqs2
 * @description TODO
 * @date 2018/9/26, Wed
 */
@Controller
@RequestMapping("/android/miao")
public class MiaoController {


    @RequestMapping(value = "/apk")
    public void update(HttpServletRequest request, HttpServletResponse response) {
        String apkPath = "/Users/lqs2/AndroidStudioProjects/CourseApp/app/build/outputs/apk/debug/app-debug.apk";
        if (OsUtils.isLinux()) {
            apkPath = "/etc/miao/app-debug.apk";
        }
        ServletContext context = request.getServletContext();
        String mimeType = context.getMimeType(apkPath);

        File downloadFile = new File(apkPath);
        if (null == mimeType) {
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
        try {
            InputStream ips = new FileInputStream(downloadFile);
            ServletOutputStream ops = response.getOutputStream();
            IOUtils.copy(ips, ops);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
