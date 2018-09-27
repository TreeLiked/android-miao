package com.ywdnf.androidmiao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ywdnf.androidmiao.entity.Memo;
import com.ywdnf.androidmiao.service.MemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author lqs2
 * @description TODO
 * @date 2018/9/18, Tue
 */
@RestController
@Slf4j
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/android/memo")
public class MemoController {


    private final MemoService service;
    private final ObjectMapper objectMapper;

    @Autowired
    public MemoController(MemoService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }


    @RequestMapping(value = "/gamm")
    public List<Memo> getAllMyMemo(String un) {
        try {
            return service.getMyMemo(un);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/gmbs")
    public String getMemoByMemoState(String un, boolean isFinished) {

        try {
            String json = objectMapper.writeValueAsString(service.findMemosByState(un, isFinished));
            log.info(json);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value = "/cmbs")
    public String changeMemoByMemoState(String un, int id, int toState, boolean isDel) {
        try {
            if (isDel) {
                return service.deleteUserMemoById(un, id);
            } else {
                return service.changeUserMemoStateById(un, id, toState);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }

    @RequestMapping(value = "/nm")
    public String newUserMemo(String un, String title, String content, int type) {
        try {
            if (null == title || "" .equals(title)) {
                title = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
            }
            return service.createNewMemo(un, title, content, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }
}
