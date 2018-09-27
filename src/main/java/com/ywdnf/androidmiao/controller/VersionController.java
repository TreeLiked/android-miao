package com.ywdnf.androidmiao.controller;

import com.ywdnf.androidmiao.utils.OsUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author lqs2
 * @description TODO
 * @date 2018/9/22, Sat
 */
@Controller
@RequestMapping(value = "/android/version")
public class VersionController {


    @RequestMapping(value = "/cu", method = RequestMethod.GET)
    @ResponseBody
    public String checkUpdate() throws IOException {
        String path = "/etc/miao/version.txt";
        if (!OsUtils.isLinux()) {
            path = "/Users/lqs2/Desktop/NewFile.txt";
        }
        File file = new File(path);
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
            return builder.toString();
        } catch (IOException e) {
            reader.close();
            e.printStackTrace();
        }
        return "-1";
    }


    @RequestMapping(value = "pu")
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
            System.out.println("context getMimeType is null");
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
            byte[] bts = new byte[1024];
            int len = -1;
            while ((len = ips.read(bts)) != -1) {
                ops.write(bts, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
