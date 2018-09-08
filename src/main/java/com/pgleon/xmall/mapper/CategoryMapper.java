package com.pgleon.xmall.mapper;

import com.pgleon.xmall.pojo.Category;
import com.pgleon.xmall.util.Page;

import java.util.List;

public interface CategoryMapper {
    List<Category> list(Page page);

    int total();
    void add(Category category);
    void delete(int id);
    Category get(int id);
    void update(Category category);
}
