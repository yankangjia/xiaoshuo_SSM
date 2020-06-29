package com.ykjzone.service;

import com.ykjzone.pojo.Chapter;
import com.ykjzone.pojo.Novel;

import java.util.List;

public interface ChapterService {
    public Chapter getChapterById(String id);

    public List<Chapter> getChaptersByNovelId(String id);

    public String getIdByNovelAndNum(Novel novel, int number);
}
