package com.ywdnf.androidmiao.controller;

import com.ywdnf.androidmiao.utils.OsUtils;
import com.ywdnf.androidmiao.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author lqs2
 * @description 哎
 * @date 2018/9/23, Sun
 */
@Controller
@RequestMapping(value = "/android/image")
@Slf4j
public class ImageController {


    @RequestMapping(value = "/ui")
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "-1";
        }
        String filename = file.getOriginalFilename();
        if (filename != null) {
            filename = UUIDUtils.generateGUID() + "." + FilenameUtils.getExtension(filename);
        }
        log.info("filename: " + filename);
        // 生成存储路径
        String baseStorageDirectory = "/Users/lqs2/Desktop/images";
        if (OsUtils.isLinux()) {
            baseStorageDirectory = "/etc/miao/images";
        }
        assert filename != null;
        String relativePath = genericRelativePath(filename, baseStorageDirectory);
        String absolutePath = baseStorageDirectory + relativePath + filename;
        File destFile = new File(absolutePath);
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(destFile);
            return relativePath + filename;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }

    // 计算文件的存放于相对根目录的相对路径
    private String genericRelativePath(String filename, String storeDirectory) {
        int hashCode = filename.hashCode();
        int dir1 = hashCode & 0xf;
        int dir2 = (hashCode & 0xf0) >> 4;
        String dir = "/" + dir1 + "/" + dir2 + "/";
        File file = new File(storeDirectory, dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dir;
    }
}
