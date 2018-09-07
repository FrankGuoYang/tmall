package com.pgleon.xmall.service.impl;

import com.pgleon.xmall.mapper.CategoryMapper;
import com.pgleon.xmall.pojo.Category;
import com.pgleon.xmall.service.CategoryService;
import com.pgleon.xmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;


    @Override
    public int total() {
        return categoryMapper.total();
    }

    @Override
    public List<Category> list(Page page) {
        return categoryMapper.list(page);
    }

    @Override
    public void add(Category category) {
        categoryMapper.add(category);
    }

    @Override
    public void delete(int id) {
        categoryMapper.delete(id);

    }

    @Override
    public Category get(int id) {
        return null;
    }

    @Override
    public void update(Category category) {

    }
}
