package com.ywdnf.androidmiao.entity;

import lombok.Data;

/**
 * @author lqs2
 * @description TODO
 * @date 2018/9/25, Tue
 */
@Data
public class UserMessage {
    private String id;
    private String msgFrom;
    private String msgTo;
    private String msgContent;
    private int msgType;
    private int msgState;
    private String postTime;

}
