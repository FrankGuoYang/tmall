package com.pgleon.xmall.service.impl;


import com.pgleon.xmall.mapper.PropertyMapper;
import com.pgleon.xmall.pojo.Property;
import com.pgleon.xmall.pojo.PropertyExample;
import com.pgleon.xmall.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiecImpl implements PropertyService {
    @Autowired
    PropertyMapper propertyMapper;

    @Override
    public void add(Property property) {
        propertyMapper.insert(property);
    }

    @Override
    public void delete(int id) {
        propertyMapper.deleteByPrimaryKey(id);

    }

    @Override
    public void update(Property property) {
        propertyMapper.updateByPrimaryKeySelective(property);

    }

    @Override
    public Property get(int id) {
        return propertyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list(int cid) {
        PropertyExample example =new PropertyExample();
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id desc");
        return propertyMapper.selectByExample(example);
    }
}
