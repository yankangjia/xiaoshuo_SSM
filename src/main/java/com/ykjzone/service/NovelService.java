package com.ykjzone.service;

import com.ykjzone.pojo.Novel;

import java.util.List;

public interface NovelService {
    public List<Novel> getRecommendNovels(Boolean getDetail);

    public List<Novel> getRecommendNovels(int category_id, Boolean getDetail);

    public List<Novel> getNewNovels();

    public List<List<Novel>> getCateHotNovels();

    public List<Novel> getRankNovels();

    public Novel getByPrimaryKey(String id);
}
