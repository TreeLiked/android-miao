package com.ywdnf.androidmiao.mapper;

import com.ywdnf.androidmiao.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author lqs2
 * @Description TODO
 * @Date 2018/9/6, Thu
 */
@Mapper
@Repository
public interface UserMapper {
    @Insert("insert into user  values (null, #{username} , #{password} , #{email}, #{isMan}, null, null)")
    Integer insertUser(@Param("username") String username, @Param("password") String password, @Param("email") String email, @Param("isMan") boolean isMan) throws Exception;

    @Insert("insert into user_fri  values (null, #{user} , #{fri}, null,  0, null)")
    Integer wantMakeFriend(@Param("user") String user_id, @Param("fri") String fri_id) throws Exception;

    @Insert("insert into user_msg  values (null, #{user} , #{fri}, #{type}, #{msg}, #{btn_text}, 0, null)")
    Integer sendMsg(@Param("user") String user_id, @Param("fri") String fri_id, @Param("type") int type, @Param("msg") String msg, @Param("btn_text") String btn_text) throws Exception;


    @Insert("insert into user_message  values (#{id}, #{userId} , #{friId}, #{content}, #{type}, 0, null)")
    Integer sendUserMessage(@Param("id") String id, @Param("userId") String userId, @Param("friId") String friId, @Param("content") String content, @Param("type") int type) throws Exception;


    @Insert("insert into friend_list values (null, #{userId}, #{friId}, null, null)")
    Integer userMakeFriend(@Param("userId") String userId, @Param("friId") String friId) throws Exception;


    @Insert("insert into user_tweet_info values(#{v1}, #{v2}, #{v3}, 0, 0)")
    Integer createUserAndTweetInfo(@Param("v1") String generateUUID, @Param("v2") String un, @Param("v3") String tweetId) throws Exception;


    @Delete("delete from user_msg where id = #{id}")
    Integer rmMsg(@Param("id") int id) throws Exception;

    @Delete("delete from user_fri where ( binary user_id = #{user_id} and binary fri_id = #{fri_id} or binary user_id = #{fri_id} and binary fri_id= #{user_id} ) and success = 1")
    Integer rmFri(@Param("user_id") String user, @Param("fri_id") String fri) throws Exception;

    @Delete("delete from user_message where id = #{id}")
    Integer deleteUserMessage(@Param("id") String id) throws Exception;


    @Delete("delete from friend_list where id = #{id}")
    Integer deleteUserFriendBy(@Param("id") String id) throws Exception;

    @Delete("delete * from user_message where binary msgFrom = #{v1} and binary msgTo = #{v2} or binary msgFrom = #{v2} and binary msgTo = #{v1}")
    void deleteUserFriendMessage(@Param("v1") String un, @Param("v2") String friId) throws Exception;


    @Update("update user_fri set success = 1 where binary user_id = #{user} and binary fri_id = #{fri} or binary user_id = #{fri} and binary fri_id = #{user}")
    Integer agreeMakeFriend(@Param("user") String u1, @Param("fri") String u2) throws Exception;

    @Update("update user_fri set fri_remark = #{re} where binary user_id = #{user} and binary fri_id = #{fri} and success = 1")
    Integer modRemark(@Param("user") String username, @Param("fri") String u1, @Param("re") String r) throws Exception;

    @Update("update user set userProfilePicStr = #{profilePicStr} where binary username = #{user_id}")
    Integer setUserProfilePic(@Param("user_id") String un, @Param("profilePicStr") String profilePicStr) throws Exception;

    @Update("update user_handoff_text set wtp_flag = 0 where binary userId = #{userId}")
    Integer changeStateOfUserHandoffText(@Param("userId") String un) throws Exception;

    @Update("update user_handoff_text set ptw_flag = 1, ptw_text = #{text} where binary userId = #{userId}")
    Integer setUserHandoffText(@Param("userId") String un, @Param("text") String text) throws Exception;

    @Update("update user_tweet_info set praise = #{v3} where binary userId = #{v1} and tweetId = #{v2}")
    Integer userPraiseTweet(@Param("v1") String un, @Param("v2") String tweetId, @Param("v3") boolean praise) throws Exception;

    @Update("update user_tweet_info set collect = #{v3} where binary userId = #{v1} and tweetId = #{v2}")
    Integer userCollectTweet(@Param("v1") String un, @Param("v2") String tweetId, @Param("v3") boolean collect) throws Exception;

    @Update("insert into user_message  values (#{id}, #{userId} , #{friId}, #{content}, #{type}, 0, null)")
    Integer updateUserMessage(@Param("id") String id, @Param("userId") String userId, @Param("friId") String friId, @Param("content") String content, @Param("type") int type) throws Exception;

    @Update("update friend_list set friendMark  = #{mark} where binary userId = #{userId} and friendId = #{friendId}")
    Integer changeFriendMark(@Param("userId") String un, @Param("friendId") String friendId, @Param("mark") String mark) throws Exception;


    @Select("select * from user where binary username = #{username} and binary password = #{password}")
    User hasMatchUser1(@Param("username") String username, @Param("password") String password) throws Exception;

    @Select("select * from user where binary email = #{email} and binary password = #{password}")
    User hasMatchUser2(@Param("email") String email, @Param("password") String password) throws Exception;

    @Select("select * from user where binary username = #{username}")
    User addUser1(@Param("username") String username) throws Exception;

    @Select("select * from user where binary email = #{email}")
    User addUser2(@Param("email") String email) throws Exception;

    @Select("select * from user where binary username = #{username} or binary email = #{username}")
    User hasMatcherUsername(@Param("username") String name) throws Exception;

    @Select("select * from user_msg where binary msg_to = #{username} order by msg_time desc")
    List<Msg> getUserMsg(@Param("username") String username) throws Exception;

    @Select("select count(*) from user_msg where binary msg_to = #{username} and msg_state = 0")
    Integer getMsgAmountByUsername(@Param("username") String username) throws Exception;

    @Select("select count(*) from user_fri where ( binary user_id = #{user_id} and binary fri_id = #{fri_id} or binary user_id = #{fri_id} and binary fri_id= #{user_id} ) and success =  1")
    Integer judgeHaveBeenFriend(@Param("user_id") String user, @Param("fri_id") String fri) throws Exception;


    @Select("select count(*) from user_fri where binary user_id = #{user_id} and binary fri_id = #{fri_id} and success = 0")
    Integer haveSendFriendReq(@Param("user_id") String user, @Param("fri_id") String fri) throws Exception;

    @Select("select userProfilePicStr from user where binary username = #{user_id}")
    String getUserProfilePic(@Param("user_id") String un) throws Exception;


    @Select("select wtp_text from user_handoff_text where binary userId = #{userId} and wtp_flag = 1")
    String getUserHandoffText(@Param("userId") String un) throws Exception;


    @Select("select friend_list.id, friend_list.friendId, friend_list.friendMark, friend_list.createTime, user.isMan, user.email from friend_list, user where binary friend_list.userId = #{userId} and binary friend_list.friendId = binary user.username order by friend_list.friendId")
    List<UserFriend> getUserFriend(@Param("userId") String un) throws Exception;

    @Select("select * from user_tweet_info where binary userId = #{userId} and tweetId = #{tweetId}")
    UserAndTweet showUserAndTweetInfo(@Param("userId") String un, @Param("tweetId") String tweetId) throws Exception;


    @Select("select * from user_message where binary msgTo = #{userId} order by postTime desc")
    List<UserMessage> getUserMessage(@Param("userId") String un) throws Exception;


}
