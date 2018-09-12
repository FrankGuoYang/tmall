package com.pgleon.xmall.service;

import com.pgleon.xmall.pojo.Category;
import com.pgleon.xmall.util.Page;

import java.util.List;

public interface CategoryService{
    List<Category> list();

    void add(Category category);

    void delete(int id);

    Category get(int id);

    void update(Category category);
}