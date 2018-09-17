package com.pgleon.xmall.service;

import com.pgleon.xmall.pojo.Product;
import com.pgleon.xmall.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueService {
    void init(Product p);
    void update(PropertyValue pv);

    PropertyValue get(int ptid, int pid);
    List<PropertyValue> list(int pid);
}
