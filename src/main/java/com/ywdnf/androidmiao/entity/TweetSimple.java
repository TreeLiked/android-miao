package com.ywdnf.androidmiao.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lqs2
 * @description TODO
 * @date 2018/9/10, Mon
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TweetSimple implements Serializable {

    private String id;
    private String userId;
    private String content;
    private String commentId;
    private String postTime;
}
