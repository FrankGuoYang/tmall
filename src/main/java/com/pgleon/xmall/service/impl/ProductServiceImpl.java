package com.pgleon.xmall.service.impl;

import com.pgleon.xmall.mapper.ProductMapper;
import com.pgleon.xmall.pojo.Category;
import com.pgleon.xmall.pojo.Product;
import com.pgleon.xmall.pojo.ProductExample;
import com.pgleon.xmall.service.CategoryService;
import com.pgleon.xmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryService categoryService;

    @Override
    public void add(Product p) {
        productMapper.insert(p);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product p) {
        productMapper.updateByPrimaryKeySelective(p);
    }

    @Override
    public Product get(int id) {
        Product product = productMapper.selectByPrimaryKey(id);
        setCategory(product);
        return product;
    }

    public void setCategory(List<Product> products){
        for (Product product:products)
            setCategory(product);

    }

    private void setCategory(Product product) {
        int cid = product.getCid();
        Category category = categoryService.get(cid);
        product.setCategory(category);
    }


    @Override
    public List list(int cid) {
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andCidEqualTo(cid);
        productExample.setOrderByClause("id desc");
        List result = productMapper.selectByExample(productExample);
        setCategory(result);
        return result;
    }
}
