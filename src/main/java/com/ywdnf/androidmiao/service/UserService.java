package com.ywdnf.androidmiao.service;

import com.ywdnf.androidmiao.entity.User;
import com.ywdnf.androidmiao.entity.UserAndTweet;
import com.ywdnf.androidmiao.entity.UserFriend;
import com.ywdnf.androidmiao.entity.UserMessage;
import com.ywdnf.androidmiao.mapper.UserMapper;
import com.ywdnf.androidmiao.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lqs2
 * @Description 用户
 * @Date 2018/9/6, Thu
 */
@Service
@Slf4j

public class UserService {


    private final UserMapper userMapper;
    private final CommonService commonService;

    @Autowired
    public UserService(UserMapper userMapper, CommonService commonService) {
        this.userMapper = userMapper;
        this.commonService = commonService;
    }

    public User userDoExist(String name) throws Exception {
        return userMapper.hasMatcherUsername(name);
    }

    public boolean isLegalUser(String un, String pwd) {
        try {
            return commonService.isLegalUser(un, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public String getUserProfilePic(String un) {
        try {
            return userMapper.getUserProfilePic(un);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean setUserProfilePic(String un, String profilePicStr) {
        try {
            return userMapper.setUserProfilePic(un, profilePicStr) == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String registerUser(String un, String pwd, String email, boolean isMan) {

        try {
            return userMapper.insertUser(un, pwd, email, isMan) == 1 ? "1" : "0";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }

    public String getUserHandoffText(String un) {
        try {
            String text = userMapper.getUserHandoffText(un);
            return null == text || "".equals(text) ? "-1" : text;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void changeStateOfUserHandoffText(String un) {
        try {
            userMapper.changeStateOfUserHandoffText(un);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String setUserHandoffText(String un, String text) {

        try {
            return userMapper.setUserHandoffText(un, text) == 1 ? "1" : "0";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }

    public List<UserFriend> getUserFriend(String un) {
        try {
            return userMapper.getUserFriend(un);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<UserMessage> getUserMessage(String un) {
        try {
            return userMapper.getUserMessage(un);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void deleteUserMessage(String id) throws Exception {
        userMapper.deleteUserMessage(id);
    }

    public void sendMakeFriMsg(String un, String friendId) throws Exception {
        userMapper.sendUserMessage(UUIDUtils.generateUUID(), un, friendId, "好友请求：[ 用户：" + un + " ] 迫切想与您成为好友", 2);
    }

    public boolean agreeMakeFriMsg(String id, String un, String friendId, boolean agree) throws Exception {
        String str = "好友请求：[ 用户：" + un + " ] 欣然接受了您的好友添加请求";
        if (!agree) {
            str = "好友请求：[ 用户：" + un + " ] 毫不留情拒绝了您的好友添加请求";
        }
        int i = 0, j = 0;
        if (agree) {
            i = userMapper.userMakeFriend(un, friendId);
            j = userMapper.userMakeFriend(friendId, un);
        }
        userMapper.sendUserMessage(UUIDUtils.generateUUID(), un, friendId, str, 2);
        userMapper.deleteUserMessage(id);
        return i == 1 && j == 1;
    }


    public UserAndTweet showUserAndTweetInfo(String userId, String tweetId) throws Exception {
        return userMapper.showUserAndTweetInfo(userId, tweetId);
    }

    public Integer userPraiseTweet(String un, String tweetId, boolean praise) throws Exception {
        if (praise) {
            return userMapper.userPraiseTweet(un, tweetId, true);
        } else {
            return userMapper.userPraiseTweet(un, tweetId, false);
        }
    }

    public Integer userCollectTweet(String un, String tweetId, boolean collect) throws Exception {
        if (collect) {
            return userMapper.userCollectTweet(un, tweetId, true);
        } else {
            return userMapper.userCollectTweet(un, tweetId, false);
        }
    }

    public void createUserAndTweetInfo(String generateUUID, String un, String tweetId) throws Exception {
        userMapper.createUserAndTweetInfo(generateUUID, un, tweetId);
    }


    public int changeFriendMark(String un, String friendId, String mark) throws Exception {

        return userMapper.changeFriendMark(un, friendId, mark);

    }

    public int deleteUserFriend(String un, String friId, String id) throws Exception {
        userMapper.deleteUserFriendMessage(un, friId);
        return userMapper.deleteUserFriendBy(id);
    }
}
