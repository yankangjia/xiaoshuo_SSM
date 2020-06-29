package com.ykjzone.mapper;

import com.ykjzone.pojo.Novel;
import com.ykjzone.pojo.NovelExample;

import java.util.List;
import java.util.Map;

public interface NovelMapper {
    int deleteByPrimaryKey(String id);

    int insert(Novel record);

    int insertSelective(Novel record);

    List<Novel> selectByExampleWithBLOBs(NovelExample example);

    List<Novel> selectByExample(NovelExample example);

    List<Novel> selectManyRelated(NovelExample ex);

    Novel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Novel record);

    int updateByPrimaryKeyWithBLOBs(Novel record);

    int updateByPrimaryKey(Novel record);
}