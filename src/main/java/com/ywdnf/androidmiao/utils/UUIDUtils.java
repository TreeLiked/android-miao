package com.ywdnf.androidmiao.utils;

import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;

/**
 * @author lqs2
 * @description TODO
 * @date 2018/9/10, Mon
 */
public class UUIDUtils {

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }


    public static String generateGUID() {
        return new BigInteger(165, new Random()).toString(36).toUpperCase();
    }
}
