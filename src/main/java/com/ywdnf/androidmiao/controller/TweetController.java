package com.ywdnf.androidmiao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ywdnf.androidmiao.service.TweetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author lqs2
 * @description 动态controller
 * @date 2018/9/8, Sat
 */
@RestController
@RequestMapping(value = "/android/tweet")
@Slf4j
public class TweetController {


    private final TweetService tweetService;
    private final ObjectMapper objectMapper;


    @Autowired
    public TweetController(TweetService tweetService, ObjectMapper mapper) {
        this.tweetService = tweetService;
        this.objectMapper = mapper;
    }


    @RequestMapping("/nt")
    public String newTweet(String un, String content,
                           String imgPath0,
                           String imgPath1,
                           String imgPath2,
                           String imgPath3,
                           String imgPath4,
                           String imgPath5,
                           String imgPath6,
                           String imgPath7,
                           String imgPath8) {
        try {
            String id = UUID.randomUUID().toString();
            return tweetService.insertOneTweet(id, un, content, "", imgPath0, imgPath1, imgPath2, imgPath3, imgPath4, imgPath5, imgPath6, imgPath7, imgPath8) ? "1" : "0";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }


    @RequestMapping("/gat")
    public String getAllTweet(int sp) {
        try {
            return objectMapper.writeValueAsString(tweetService.getAllTweets(sp));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    @RequestMapping("/dt")
    public String deleteTweet(String id) {

        try {
            tweetService.deleteTweetInfoByTweetId(id);
            return tweetService.deleteTweetById(id) == 1 ? "1" : "0";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }


    @RequestMapping("/mtg")
    public void modifyTweetGood(String id, boolean add) {
        try {
            if (add) {
                tweetService.addTweetGood(id);
            } else {
                tweetService.reduceTweetGood(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
