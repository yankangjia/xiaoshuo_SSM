package com.ykjzone.service.impl;

import com.ykjzone.mapper.CategoryMapper;
import com.ykjzone.pojo.Category;
import com.ykjzone.pojo.CategoryExample;
import com.ykjzone.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategories() {
        return categoryMapper.selectByExample(new CategoryExample());
    }

    @Override
    public List<Category> getCategoriesAndTags() {
        return categoryMapper.selectAndTags();
    }

    @Override
    public Category getOneAndTags(String id) {
        return categoryMapper.selectOneAndTags(id);
    }

    @Override
    public List<Category> getAndNovelsNum() {
        return categoryMapper.selectAndNovelsNum();
    }

    @Override
    public List<Category> getByExample(CategoryExample example) {
        return categoryMapper.selectByExample(example);
    }

    @Override
    public Category getAndTagsByName(String name) {
        return categoryMapper.selectAndTagsByName(name);
    }

    @Override
    public int insert(Category category) {
        return categoryMapper.insert(category);
    }

    @Override
    public int updateById(Category category) {
        return categoryMapper.updateByPrimaryKey(category);
    }

    @Override
    public int deleteById(Integer id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }
}
