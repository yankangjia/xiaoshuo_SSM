package com.ykjzone.service.impl;

import com.ykjzone.mapper.ChapterMapper;
import com.ykjzone.pojo.Chapter;
import com.ykjzone.pojo.Novel;
import com.ykjzone.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    ChapterMapper chapterMapper;

    @Override
    public Chapter getChapterById(String id) {
        Chapter chapter = chapterMapper.selectByPrimaryKey(id);
        return chapter;
    }

    @Override
    public List<Chapter> getChaptersByNovelId(String id) {
        List<Chapter> chapters = chapterMapper.selectChaptersByNovelId(id);
        return chapters;
    }

    @Override
    public String getIdByNovelAndNum(Novel novel, int number) {
        String chapter_id = chapterMapper.selectIdByNovelAndNum(novel.getId(),number);
        return chapter_id;
    }
}
