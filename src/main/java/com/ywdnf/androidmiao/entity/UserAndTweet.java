package com.ywdnf.androidmiao.entity;

import lombok.Data;

/**
 * @author lqs2
 * @description TODO
 * @date 2018/9/20, Thu
 */
@Data
public class UserAndTweet {

    private String id;
    private String userId;
    private String tweetId;
    private boolean praise;
    private boolean collect;
}
