package com.ywdnf.androidmiao.mapper;

import com.ywdnf.androidmiao.entity.UserMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lqs2
 * @description TODO
 * @date 2018/9/25, Tue
 */
@Mapper
@Repository
public interface MessageMapper {

    @Select("select * from user_message where binary msgFrom = #{userId} and binary msgTo = #{friId} or msgTo = #{userId} and binary msgFrom = #{friId}")
    List<UserMessage> queryMessageByUserAndFriend(@Param("userId") String userId, @Param("friId") String friId) throws Exception;
}
