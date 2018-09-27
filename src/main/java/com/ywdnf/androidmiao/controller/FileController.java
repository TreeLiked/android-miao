package com.ywdnf.androidmiao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ywdnf.androidmiao.entity.File;
import com.ywdnf.androidmiao.mapper.FileMapper;
import com.ywdnf.androidmiao.mapper.UserMapper;
import com.ywdnf.androidmiao.service.FileService;
import com.ywdnf.androidmiao.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author lqs2
 * @Description TODO
 * @Date 2018/6/24, Sun
 */
@RestController
@Slf4j
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/android/file")
public class FileController {


    private final FileService fileService;
    private final ObjectMapper mapper;
    private final UserMapper userMapper;
    private final FileMapper fileMapper;

    @Autowired
    public FileController(FileService fileService, ObjectMapper mapper, UserMapper userMapper, FileMapper fileMapper) {
        this.fileService = fileService;
        this.mapper = mapper;
        this.userMapper = userMapper;
        this.fileMapper = fileMapper;
    }


    @RequestMapping(value = "/gmf")
    public String getMyFile(String un) {
        try {
            List<File> myFile = fileService.getMyFile(un);
            if (null != myFile) {
                return mapper.writeValueAsString(myFile);
            } else {
                return "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }

    @RequestMapping(value = "/smf")
    public String searchMyFile(String un, String fileNo) {
        log.info("{}, 搜索文件", fileNo);
        try {
            File file = fileService.bringFileOut(fileNo);
            if (file != null) {
                if (file.getFile_destination() != null && !"".equals(file.getFile_destination())) {
                    if ((un.equals(file.getFile_destination()) || file.getFile_post_author().equals(un))) {
                        return mapper.writeValueAsString(file);
                    }
                    return "no";
                }
                return mapper.writeValueAsString(file);
            }
            return "null";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }

    @RequestMapping(value = "/rmf")
    public String removeFile(String un, String file_id) {
        Integer result;
        try {
            result = fileService.removeFileById(un, file_id);
            return result == 1 ? "1" : "0";
        } catch (Exception e) {
            log.error(e.toString());
        }
        return "0";
    }


    @RequestMapping(value = "/gnf", method = {RequestMethod.GET})
    public String generateNewFile(String filename) {
        Integer r1;
        try {
            r1 = fileMapper.getFileRandomNo();
            String suffix = filename.substring(filename.lastIndexOf(".") + 1);
            if (r1 != 0) {
                return r1.toString() + "/" + r1 + "." + suffix;
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
        return "0";
    }


    @RequestMapping(value = "/dfc")
    public void deleteFileCloud(String bucket_id) {


    }

    @RequestMapping(value = "/ifr", method = {RequestMethod.POST})
    public String insertFileRecord(String filePostAuthor, int fileNo, String fileName, String attachment, String destination, String fileSize) {
        log.info("添加文件记录{}", fileSize);
        log.info(filePostAuthor);
        log.info(String.valueOf(fileNo));
        log.info(fileName);
        log.info(attachment);
        log.info(destination);
        log.info(fileSize);
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        try {
            if (!StringUtils.isEmpty(destination)) {
                userMapper.sendMsg(filePostAuthor, destination, 2, "收到一个文件【 名称：" + fileName + "，大小：" + fileSize + "，编号：" + fileNo + " 】，来源：" + filePostAuthor, "null");
                userMapper.sendUserMessage(UUIDUtils.generateUUID(), filePostAuthor, destination, "收到一个文件： [ 来自： " + filePostAuthor + " ] ", 3);
            }
            Integer integer = fileService.insertFileRecord(String.valueOf(fileNo), fileNo + "." + suffix, 365, fileName, attachment, destination, fileSize, filePostAuthor);
            return integer == 1 ? "1" : "0";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

}
