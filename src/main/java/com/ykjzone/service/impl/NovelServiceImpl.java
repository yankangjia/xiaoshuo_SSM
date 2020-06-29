package com.ykjzone.service.impl;

import com.github.pagehelper.PageHelper;
import com.ykjzone.mapper.NovelMapper;
import com.ykjzone.pojo.Category;
import com.ykjzone.pojo.Novel;
import com.ykjzone.pojo.NovelExample;
import com.ykjzone.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NovelServiceImpl implements NovelService {
    @Autowired
    NovelMapper novelMapper;

    public List<Novel> getRecommendNovels(Boolean getDetail) {
        NovelExample example = new NovelExample();
        NovelExample.Criteria criteria = example.createCriteria();
        criteria.andIs_recommendEqualTo(true);
        example.setOrderByClause("pub_date desc");
        List<Novel> novels = new ArrayList<>();
        if(getDetail){
            novels = novelMapper.selectByExampleWithBLOBs(example);
        }else{
            novels = novelMapper.selectManyRelated(example);
        }
        return novels;
    }

    @Override
    public List<Novel> getRecommendNovels(int category_id, Boolean getDetail) {
        NovelExample example = new NovelExample();
        NovelExample.Criteria criteria = example.createCriteria();
        criteria.andIs_recommendEqualTo(true);
        criteria.andCategory_idEqualTo(category_id);
        example.setOrderByClause("pub_date desc");
        List<Novel> novels = new ArrayList<>();
        if(getDetail){
            novels = novelMapper.selectByExampleWithBLOBs(example);
        }else{
            novels = novelMapper.selectManyRelated(example);
        }
        return novels;
    }

    public List<Novel> getNewNovels() {
        NovelExample example = new NovelExample();
        example.setOrderByClause("pub_date desc");
        List<Novel> novels = novelMapper.selectManyRelated(example);
        return novels;
    }

    public List<List<Novel>> getCateHotNovels(){
        String[][] all_category_name = Category.CATEGORY_NAME;
        List<List<Novel>> cate_hot_novels = new ArrayList<List<Novel>>();
        for(int i = 0; i < 6; i++){
            int id1 = i*2+1;
            int id2 = i*2+2;

            NovelExample example = new NovelExample();
            example.setOrderByClause("views desc");

            NovelExample.Criteria criteria = example.createCriteria();
            List<Integer> ids = new ArrayList<>();
            ids.add(id1);
            ids.add(id2);
            criteria.andCategory_idIn(ids);

            PageHelper.offsetPage(0, 5);
            List<Novel> novels = novelMapper.selectManyRelated(example);
            cate_hot_novels.add(novels);
        }
        return cate_hot_novels;
    }

    public List<Novel> getRankNovels(){
        NovelExample example = new NovelExample();
        example.setOrderByClause("views desc");
        List<Novel> novels = novelMapper.selectManyRelated(example);
        return novels;
    }

    public Novel getByPrimaryKey(String id){
        Novel novel = novelMapper.selectByPrimaryKey(id);
        return novel;
    }
}
