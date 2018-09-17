package com.pgleon.xmall.service.impl;

import com.pgleon.xmall.mapper.ProductImageMapper;
import com.pgleon.xmall.pojo.ProductImage;
import com.pgleon.xmall.pojo.ProductImageExample;
import com.pgleon.xmall.service.CategoryService;
import com.pgleon.xmall.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    ProductImageMapper productImageMapper;

    @Autowired
    CategoryService categoryService;


    @Override
    public void add(ProductImage productImage) {
        productImageMapper.insert(productImage);
    }

    @Override
    public void delete(int id) {
        productImageMapper.deleteByPrimaryKey(id);

    }

    @Override
    public void update(ProductImage productImage) {
        productImageMapper.updateByPrimaryKeySelective(productImage);
    }

    @Override
    public ProductImage get(int id) {

        return productImageMapper.selectByPrimaryKey(id);
    }


    @Override
    public List list(int pid, String type) {
        ProductImageExample example = new ProductImageExample();
        example.createCriteria()
                .andPidEqualTo(pid)
                .andTypeEqualTo(type);
        example.setOrderByClause("id desc");
        return productImageMapper.selectByExample(example);
    }

}
