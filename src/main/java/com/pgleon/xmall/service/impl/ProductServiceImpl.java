package com.pgleon.xmall.service.impl;

import com.pgleon.xmall.mapper.ProductMapper;
import com.pgleon.xmall.pojo.Category;
import com.pgleon.xmall.pojo.Product;
import com.pgleon.xmall.pojo.ProductExample;
import com.pgleon.xmall.pojo.ProductImage;
import com.pgleon.xmall.service.CategoryService;
import com.pgleon.xmall.service.ProductImageService;
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
    @Autowired
    ProductImageService productImageService;

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

    @Override
    public void setFirstProductImage(Product p) {
        List<ProductImage> pis = productImageService.list(p.getId(), ProductImageService.type_single);
        if (!pis.isEmpty()) {
            ProductImage pi = pis.get(0);
            p.setFirstProductImage(pi);
        }
    }

//     为分类填充产品集合
    @Override
    public void fill(List<Category> categorys) {
        for (Category category : categorys) {
            fill(category);
        }

    }

    @Override
    public void fill(Category category) {
        List<Product> ps = list(category.getId());
        category.setProducts(ps);
    }

    @Override
    public void fillByRow(List<Category> categorys) {

    }

    public void setFirstProductImage(List<Product> ps) {
        for (Product p : ps) {
            setFirstProductImage(p);
        }
    }
}
