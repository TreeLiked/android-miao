package com.ywdnf.androidmiao.mapper;

import com.ywdnf.androidmiao.entity.Memo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author lqs2
 * @Description TODO
 * @Date 2018/7/25, Wed
 */
@Mapper
@Repository
public interface MemoMapper {

    @Select("select * from memo_lists where binary memo_author = #{author} and memo_state = #{state} order by memo_post_date desc")
    List<Memo> findMemosByState(@Param("author") String username, @Param("state") boolean isFinished) throws  Exception;

    @Select("select * from memo_lists where binary memo_author = #{author} order by memo_post_date desc")
    List<Memo> findAllMyMemo(@Param("author") String username) throws  Exception;


    @Delete("delete from memo_lists where id = #{id} and binary memo_author = #{userId}")
    Integer removeMemoById(@Param("userId") String un, @Param("id") int id) throws Exception;



    @Update("update memo_lists set memo_state = #{state} where id = #{id} and binary memo_author = #{userId}")
    Integer modifyMemoStateById(@Param("userId") String un, @Param("id") int id, @Param("state") int toState) throws Exception;

    @Insert("insert into memo_lists values(null, #{author}, #{title}, #{content}, #{type}, 0, null)")
    Integer addNewMemo(@Param("author") String username, @Param("title") String u1, @Param("content") String u2, @Param("type") int u3) throws Exception;

    @Select("select * from memo_lists where binary memo_author = #{author} and (memo_title like CONCAT('%','${value}','%' ) or memo_content like CONCAT('%','${value}','%' ))")
    List<Memo> searchMemo(@Param("author") String username, @Param("value") String u1) throws Exception;


    @Select("select count(*) from memo_lists where binary memo_author = #{author}")
    Integer getMemoAmountByUsername(@Param("author") String username) throws Exception;
}
