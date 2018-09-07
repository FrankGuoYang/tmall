package com.pgleon.xmall.service;

import com.pgleon.xmall.pojo.Category;
import com.pgleon.xmall.util.Page;

import java.util.List;

public interface CategoryService{
    int total();
    List<Category> list(Page page);

    void add(Category category);

    void delete(int id);

    Category get(int id);

    void update(Category category);
}