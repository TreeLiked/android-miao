package com.ywdnf.androidmiao.entity;

import lombok.Data;

/**
 * @author lqs2
 * @description TODO
 * @date 2018/9/20, Thu
 */
@Data
public class UserFriend {

    private String id;
    private String friendId;
    private String friendMark;
    private String createTime;


    private boolean isMan;
    private String email;
}
