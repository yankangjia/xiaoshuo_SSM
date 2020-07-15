package com.ykjzone.service.impl;

import com.ykjzone.mapper.UserReadMapper;
import com.ykjzone.service.ReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadServiceImpl implements ReadService {
    @Autowired
    UserReadMapper userReadMapper;

    @Override
    public int deleteByNovelId(String novel_id) {
        return userReadMapper.deleteByNovelId(novel_id);
    }
}
