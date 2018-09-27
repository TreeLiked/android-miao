package com.ywdnf.androidmiao.service;

import com.ywdnf.androidmiao.entity.File;
import com.ywdnf.androidmiao.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lqs2
 * @description TODO
 * @date 2018/9/19, Wed
 */
@Service
public class FileService {

    private final FileMapper fileMapper;

    @Autowired
    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }


    public List<File> getMyFile(String un) throws Exception {

        return fileMapper.getFileByUser(un);

    }

    public File bringFileOut(String fileNo) throws Exception {
        return fileMapper.bringFileOut(fileNo);
    }

    public Integer removeFileById(String un, String fileId) throws Exception {

        return fileMapper.removeFileById(un, fileId);

    }

    public Integer insertFileRecord(String valueOf, String s, int i, String fileName, String attachment, String destination, String fileSize, String filePostAuthor) throws Exception {

        return fileMapper.insertFileRecord(valueOf, s, i, fileName, attachment, destination, fileSize, filePostAuthor);
    }
}
