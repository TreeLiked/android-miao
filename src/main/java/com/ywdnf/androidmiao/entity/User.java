package com.ywdnf.androidmiao.entity;

import lombok.Data;

/**
 * @Author lqs2
 * @Description TODO
 * @Date 2018/7/16, Mon
 */
@Data
public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private boolean isMan;
    private String createTime;



}
