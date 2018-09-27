package com.ywdnf.androidmiao.service;

import com.ywdnf.androidmiao.entity.UserFriend;
import com.ywdnf.androidmiao.entity.UserMessage;
import com.ywdnf.androidmiao.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lqs2
 * @description TODO
 * @date 2018/9/25, Tue
 */
@Service
public class MessageService {


    private final MessageMapper mapper;

    @Autowired
    public MessageService(MessageMapper mapper) {
        this.mapper = mapper;
    }


    public List<UserMessage> queryMessageByUserAndFriend(String userId, String firId) throws Exception {

        return mapper.queryMessageByUserAndFriend(userId, firId);
    }
}
