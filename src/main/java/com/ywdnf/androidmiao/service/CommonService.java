package com.ywdnf.androidmiao.service;

import com.ywdnf.androidmiao.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author lqs2
 * @Description TODO
 * @Date 2018/9/6, Thu
 */
@Service
@Slf4j
public class CommonService {


    private final UserMapper userMapper;

    @Autowired
    public CommonService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    public boolean isLegalUser(String username, String password) throws Exception {
        String emailAccountFlag = "@";
        if (!username.contains(emailAccountFlag)) {
            return userMapper.hasMatchUser1(username, password) != null;
        } else {
            return userMapper.hasMatchUser2(username, password) != null;
        }
    }
}
