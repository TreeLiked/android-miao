package com.ywdnf.androidmiao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ywdnf.androidmiao.entity.UserMessage;
import com.ywdnf.androidmiao.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lqs2
 * @description TODO
 * @date 2018/9/25, Tue
 */
@RestController
@Slf4j
@Transactional(rollbackFor = RuntimeException.class)
@RequestMapping("/android/msg")
public class MessageController {


    private final MessageService messageService;
    private final ObjectMapper objectMapper;

    @Autowired
    public MessageController(MessageService messageService, ObjectMapper objectMapper) {
        this.messageService = messageService;
        this.objectMapper = objectMapper;
    }

    @RequestMapping("/qufm")
    public String queryUserFriendMessage(String un, String friId){
        List<UserMessage> msgList;
        try {
            msgList = messageService.queryMessageByUserAndFriend(un, friId);
            if (null != msgList) {
                return objectMapper.writeValueAsString(msgList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }
}
