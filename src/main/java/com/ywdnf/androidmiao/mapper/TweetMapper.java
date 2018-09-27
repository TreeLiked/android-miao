package com.ywdnf.androidmiao.mapper;

import com.ywdnf.androidmiao.entity.Tweet;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lqs2
 * @description TODO
 * @date 2018/9/10, Mon
 */
@Mapper
@Repository
public interface TweetMapper {


    @Insert("insert into tweets values (#{id}, #{userId}, #{content}, 0, #{commentId}, #{p0}, #{p1}, #{p2}, #{p3}, #{p4}, #{p5}, #{p6}, #{p7}, #{p8}, null)")
    Integer insertOneTweet(
            @Param("id") String id,
            @Param("userId") String userId,
            @Param("content") String content,
            @Param("commentId") String commentId,
            @Param("p0") String p0,
            @Param("p1") String p1,
            @Param("p2") String p2,
            @Param("p3") String p3,
            @Param("p4") String p4,
            @Param("p5") String p5,
            @Param("p6") String p6,
            @Param("p7") String p7,
            @Param("p8") String p8
    ) throws Exception;


    @Select("select tweets.id, tweets.userId, user.userProfilePicStr, tweets.content, tweets.good, tweets.commentId, tweets.imgPath0, tweets.imgPath1, tweets.imgPath2, tweets.imgPath3, tweets.imgPath4, tweets.imgPath5, tweets.imgPath6, tweets.imgPath7, tweets.imgPath8, tweets.postTime from tweets, user where binary tweets.userId = binary user.username order by tweets.postTime desc limit #{sp}, 5")
    List<Tweet> getAllTweets(@Param("sp") int startPosition) throws Exception;


    @Update("update tweets set good = good + 1 where id = #{id}")
    void addTweetGood(@Param("id") String id) throws Exception;

    @Update("update tweets set good = good - 1 where id = #{id}")
    void reduceTweetGood(@Param("id") String id) throws Exception;

    @Delete("delete from tweets where id  = #{id}")
    Integer deleteTweetById(@Param("id") String id) throws Exception;

    @Delete("delete from user_tweet_info where tweetId = #{id}")
    Integer deleteTweetInfoByTweetId(@Param("id") String id);
}
