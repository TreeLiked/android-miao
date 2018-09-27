package com.ywdnf.androidmiao.service;

import com.ywdnf.androidmiao.entity.Memo;
import com.ywdnf.androidmiao.mapper.MemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lqs2
 * @description TODO
 * @date 2018/9/18, Tue
 */
@Service
public class MemoService {

    private final MemoMapper memoMapper;

    @Autowired
    public MemoService(MemoMapper memoMapper) {
        this.memoMapper = memoMapper;
    }


    public List<Memo> getMyMemo(String un) throws Exception {
        return memoMapper.findAllMyMemo(un);
    }

    public List<Memo> findMemosByState(String un, boolean isFinished) throws Exception {
        return memoMapper.findMemosByState(un, isFinished);
    }

    public String deleteUserMemoById(String un, int id) throws Exception {
        return memoMapper.removeMemoById(un, id) == 1 ? "1" : "0";
    }

    public String changeUserMemoStateById(String un, int id, int toState) throws Exception {
        return memoMapper.modifyMemoStateById(un, id, toState) == 1 ? "1" : "0";
    }

    public String createNewMemo(String un, String title, String content, int type) throws Exception {
        return memoMapper.addNewMemo(un, title, content, type) == 1 ? "1" : "0";
    }
}
