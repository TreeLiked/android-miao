package com.ywdnf.androidmiao.service;

import com.ywdnf.androidmiao.entity.Tweet;
import com.ywdnf.androidmiao.mapper.TweetMapper;
import com.ywdnf.androidmiao.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lqs2
 * @description 帖子/动态
 * @date 2018/9/7, Fri
 */
@Service
@Slf4j
public class TweetService {


    private final TweetMapper tweetMapper;
    private final UserMapper userMapper;


    @Autowired
    public TweetService(TweetMapper tweetMapper, UserMapper userMapper) {
        this.tweetMapper = tweetMapper;
        this.userMapper = userMapper;
    }

    public boolean insertOneTweet(String id, String un, String content, String commentId,
                                  String imgPath0,
                                  String imgPath1,
                                  String imgPath2,
                                  String imgPath3,
                                  String imgPath4,
                                  String imgPath5,
                                  String imgPath6,
                                  String imgPath7,
                                  String imgPath8) throws Exception {


        return tweetMapper.insertOneTweet(id, un, content, commentId, imgPath0, imgPath1, imgPath2, imgPath3, imgPath4, imgPath5, imgPath6, imgPath7, imgPath8) == 1;

    }

    public List<Tweet> getAllTweets(int startPosition) throws Exception {
        return tweetMapper.getAllTweets(startPosition);
    }

    public void addTweetGood(String id) throws Exception {

        tweetMapper.addTweetGood(id);
    }

    public void reduceTweetGood(String id) throws Exception {

       tweetMapper.reduceTweetGood(id);
    }

    public int deleteTweetById(String id) throws Exception {

        return tweetMapper.deleteTweetById(id);
    }

    public int deleteTweetInfoByTweetId(String id) throws Exception {

        return tweetMapper.deleteTweetInfoByTweetId(id);
    }
}
