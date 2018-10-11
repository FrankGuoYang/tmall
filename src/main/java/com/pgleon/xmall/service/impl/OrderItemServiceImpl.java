package com.pgleon.xmall.service.impl;

import com.pgleon.xmall.mapper.OrderItemMapper;
import com.pgleon.xmall.pojo.Order;
import com.pgleon.xmall.pojo.OrderItem;
import com.pgleon.xmall.pojo.OrderItemExample;
import com.pgleon.xmall.pojo.Product;
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
        orderItemMapper.insert(c);
    }

    @Override
    public void delete(int id) {
        orderItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(OrderItem c) {
        orderItemMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public OrderItem get(int id) {
        OrderItem result = orderItemMapper.selectByPrimaryKey(id);
        setProduct(result);
        return result;
    }


    @Override
    public List<OrderItem> list() {
        OrderItemExample example = new OrderItemExample();
        example.setOrderByClause("id desc");
        return orderItemMapper.selectByExample(example);
    }

    @Override
    public void fill(List<Order> os) {
        for (Order order : os){
            fill(order);
        }

    }

    @Override
    public void fill(Order o) {
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andOidEqualTo(o.getId());
        example.setOrderByClause("id desc");
        List<OrderItem> ois = orderItemMapper.selectByExample(example);
        setProduct(ois);
        float total = 0;
        int totalNumber = 0;
        for (OrderItem oi:ois){
            total+=oi.getNumber()*oi.getProduct().getPromotePrice();
            totalNumber+=oi.getNumber();
        }
        o.setTotal(total);
        o.setTotalNumber(totalNumber);
        o.setOrderItems(ois);
    }

    @Override
    public int getSaleCount(int pid) {
        OrderItemExample example =new OrderItemExample();
        example.createCriteria().andPidEqualTo(pid);
        List<OrderItem> ois =orderItemMapper.selectByExample(example);
        int result =0;
        for (OrderItem oi : ois) {
            result+=oi.getNumber();
        }
        return result;
    }

    @Override
    public List<OrderItem> listByUser(int uid) {
        System.out.println("listByUser");
        OrderItemExample example =new OrderItemExample();
        System.out.println(example);
        example.createCriteria().andUidEqualTo(uid).andOidIsNull();
        System.out.println("preaper");
        List<OrderItem> result =orderItemMapper.selectByExample(example);
        System.out.println(result.size());
        System.out.println(result.get(0).getId());
        setProduct(result);
        System.out.println("setOK");
        return result;
    }

    public void setProduct(List<OrderItem> ois){
        System.out.println("setList");
        for (OrderItem oi: ois){
            setProduct(oi);
        }
    }

    private void setProduct(OrderItem oi) {
        System.out.println("setItem1");
        Product product = productService.get(oi.getPid());
        System.out.println("get product");
        oi.setProduct(product);
        System.out.println("setItem finish");
    }

}
