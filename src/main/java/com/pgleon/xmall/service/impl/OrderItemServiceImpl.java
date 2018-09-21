package com.pgleon.xmall.service.impl;

import com.pgleon.xmall.mapper.OrderItemMapper;
import com.pgleon.xmall.pojo.Order;
import com.pgleon.xmall.pojo.OrderItem;
import com.pgleon.xmall.service.OrderItemService;
import com.pgleon.xmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ProductService productService;
    
    @Override
    public void add(OrderItem c) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(OrderItem c) {

    }

    @Override
    public OrderItem get(int id) {
        return null;
    }

    @Override
    public List list() {
        return null;
    }

    @Override
    public void fill(List<Order> os) {

    }

    @Override
    public void fill(Order o) {

    }
}
