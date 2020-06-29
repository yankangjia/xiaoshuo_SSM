package com.ykjzone.mapper;

import com.ykjzone.pojo.UserCollect;
import com.ykjzone.pojo.UserCollectExample;

import java.util.List;

public interface UserCollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCollect record);

    int insertSelective(UserCollect record);

    List<UserCollect> selectByExample(UserCollectExample example);

    UserCollect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCollect record);

    int updateByPrimaryKey(UserCollect record);
}