package com.pgleon.xmall.service.impl;

import com.pgleon.xmall.mapper.ReviewMapper;
import com.pgleon.xmall.pojo.Review;
import com.pgleon.xmall.service.ReviewService;
import com.pgleon.xmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    UserService userService;

    @Override
    public void add(Review review) {
        reviewMapper.insert(review);
    }

    @Override
    public void delete(int id) {
        reviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Review review) {
        reviewMapper.updateByPrimaryKeySelective(review);
    }

    @Override
    public Review get(int id) {
        return null;
    }

    @Override
    public List list(int pid) {
        return null;
    }

    @Override
    public int getCount(int pid) {
        return 0;
    }
}
