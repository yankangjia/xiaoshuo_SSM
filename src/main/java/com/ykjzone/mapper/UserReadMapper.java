package com.ykjzone.mapper;

import com.ykjzone.pojo.UserRead;
import com.ykjzone.pojo.UserReadExample;

import java.util.List;

public interface UserReadMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRead record);

    int insertSelective(UserRead record);

    List<UserRead> selectByExample(UserReadExample example);

    UserRead selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRead record);

    int updateByPrimaryKey(UserRead record);

    int deleteByNovelId(String novel_id);
}