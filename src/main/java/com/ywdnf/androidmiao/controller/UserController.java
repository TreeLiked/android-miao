package com.ywdnf.androidmiao.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ywdnf.androidmiao.entity.User;
import com.ywdnf.androidmiao.entity.UserAndTweet;
import com.ywdnf.androidmiao.entity.UserFriend;
import com.ywdnf.androidmiao.entity.UserMessage;
import com.ywdnf.androidmiao.service.UserService;
import com.ywdnf.androidmiao.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author lqs2
 * @description UserController
 * @date 2018/9/6, Thu
 */
@RestController
@RequestMapping(value = "/android/user")
@Transactional
public class UserController {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }


    @RequestMapping(value = "/ude", method = RequestMethod.GET)
    public String userDoExist(String name) throws Exception {
        User user = userService.userDoExist(name);
        if (null != user) {
            return objectMapper.writeValueAsString(user);
        }
        return "0";
    }

    @RequestMapping(value = "/urv", method = RequestMethod.POST)
    public String userRegisterValidate(String un, String pwd, String email, String isMan) {
        try {
            if (!"0".equals(userService.userDoExist(un))) {
                return userService.registerUser(un, pwd, email, Integer.parseInt(isMan) == 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    @RequestMapping(value = "/ulv", method = RequestMethod.POST)
    public String userLoginValidate(String un, String pwd) {
        return userService.isLegalUser(un, pwd) ? "1" : "0";
    }


    @RequestMapping(value = "/gupp", method = RequestMethod.GET)
    public String getUserProfilePicture(String un) {
        String str = userService.getUserProfilePic(un);
        return str != null ? str : "0";
    }

    @RequestMapping(value = "/supp", method = RequestMethod.POST)
    public String setUserProfilePicture(String un, String profilePicStr) {
        return userService.setUserProfilePic(un, profilePicStr) ? "1" : "0";
    }

    @RequestMapping(value = "/guht", method = {RequestMethod.GET, RequestMethod.POST})
    public String getUserHandoffText(String un) {
        return userService.getUserHandoffText(un);
    }

    @RequestMapping(value = "/suht", method = {RequestMethod.GET, RequestMethod.POST})
    public String setUserHandoffText(String un, String text) {
        return userService.setUserHandoffText(un, text);
    }

    @RequestMapping(value = "/offuht", method = RequestMethod.GET)
    public void offUserHandoffText(String un) {
        userService.changeStateOfUserHandoffText(un);
    }


    @RequestMapping(value = "/gmf", method = {RequestMethod.GET})
    public String getMyFriend(String un) {
        try {
            List<UserFriend> userFriend = userService.getUserFriend(un);
            if (null != userFriend) {
                return objectMapper.writeValueAsString(userFriend);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "-1";
    }


    @RequestMapping(value = "/gum", method = {RequestMethod.GET})
    public String getUserMessage(String un) {
        try {
            List<UserMessage> userFriend = userService.getUserMessage(un);
            if (null != userFriend) {
                return objectMapper.writeValueAsString(userFriend);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "-1";
    }

    @RequestMapping(value = "/dum", method = {RequestMethod.GET})
    public void deleteUserMessage(String id) {
        try {
            userService.deleteUserMessage(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/smfm", method = {RequestMethod.GET})
    public void sendMakeFriendMsg(String un, String friendId) {
        try {
            userService.sendMakeFriMsg(un, friendId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/amfm", method = {RequestMethod.GET})
    public String agreeMakeFriendMsg(String id, String un, String friendId, boolean agree) {
        try {
            return userService.agreeMakeFriMsg(id, un, friendId, agree) ? "1" : "0";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }

    @RequestMapping(value = "/cfm", method = {RequestMethod.GET})
    public String changeFriendMark(String un, String friendId, String mark) {

        try {
            return userService.changeFriendMark(un, friendId, mark) == 1 ? "1" : "0";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }

    @RequestMapping(value = "/duf", method = RequestMethod.GET)
    public String deleteUserFriend(String userId, String friendId, String id) {
        try {
            return userService.deleteUserFriend(userId, friendId, id) == 1 ? "1" : "0";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }


    @RequestMapping(value = "/guti", method = {RequestMethod.GET})
    public String getUserTweetInfo(String un, String tweetId) throws JsonProcessingException {
        try {
            UserAndTweet userAndTweet = userService.showUserAndTweetInfo(un, tweetId);
            if (null != userAndTweet) {
                return objectMapper.writeValueAsString(userAndTweet);
            } else {
                userService.createUserAndTweetInfo(UUIDUtils.generateUUID(), un, tweetId);
                return "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }

    @RequestMapping(value = "/upt", method = {RequestMethod.GET})
    public Integer userPraiseTweet(String un, String tweetId, boolean praise) {
        try {
            return userService.userPraiseTweet(un, tweetId, praise);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

//    @RequestMapping(value = "/ucpt", method = {RequestMethod.GET})
//    public Integer userCancelPraiseTweet(String un, String tweetId) {
//        try {
//            return userService.userPraiseTweet(un, tweetId, false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }


    @RequestMapping(value = "/uct", method = {RequestMethod.GET})
    public Integer userCollectTweet(String un, String tweetId, boolean collect) {
        try {
            return userService.userCollectTweet(un, tweetId, collect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

//    @RequestMapping(value = "/ucct", method = {RequestMethod.GET})
//    public Integer userCancelCollectTweet(String un, String tweetId) {
//        try {
//            return userService.userCollectTweet(un, tweetId, false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }

}