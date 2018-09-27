package com.ywdnf.androidmiao.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lqs2
 * @description 动态实体
 * @date 2018/9/7, Fri
 */

@Getter
@Setter
public class Tweet {

    private String id;
    // tweet的UUID

    private String userId;
    private String userProfilePicStr;
    private String content;
    private int good;
    /**
     * 此动态的评论id
     */
    private String commentId;
    private String imgPath0;
    private String imgPath1;
    private String imgPath2;
    private String imgPath3;
    private String imgPath4;
    private String imgPath5;
    private String imgPath6;
    private String imgPath7;
    private String imgPath8;


    private String postTime;

    @Override
    public String toString() {
        return "Tweet{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", good=" + good +
                ", commentId='" + commentId + '\'' +
                ", imgPath0='" + imgPath0 + '\'' +
                ", imgPath1='" + imgPath1 + '\'' +
                ", imgPath2='" + imgPath2 + '\'' +
                ", imgPath3='" + imgPath3 + '\'' +
                ", imgPath4='" + imgPath4 + '\'' +
                ", imgPath5='" + imgPath5 + '\'' +
                ", imgPath6='" + imgPath6 + '\'' +
                ", imgPath7='" + imgPath7 + '\'' +
                ", imgPath8='" + imgPath8 + '\'' +
                ", postTime='" + postTime + '\'' +
                '}';
    }
}
