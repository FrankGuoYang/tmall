package com.pgleon.xmall.service.impl;

import com.pgleon.xmall.mapper.OrderMapper;
import com.pgleon.xmall.pojo.Order;
import com.pgleon.xmall.pojo.OrderExample;
import com.pgleon.xmall.pojo.OrderItem;
import com.pgleon.xmall.pojo.User;
import com.pgleon.xmall.service.OrderItemService;
import com.pgleon.xmall.service.OrderService;
import com.pgleon.xmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserService userService;

    @Autowired
    OrderItemService orderItemService;


    @Override
    public void add(Order c) {
        orderMapper.insert(c);
    }

    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    @Override
    public float add(Order c, List<OrderItem> ois) {
        float total = 0;
        add(c);

        if(false)
            throw new RuntimeException();

        for (OrderItem oi: ois) {
            oi.setOid(c.getId());
            orderItemService.update(oi);
            total+=oi.getProduct().getPromotePrice()*oi.getNumber();
        }
        return total;
    }

    @Override
    public void delete(int id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order c) {
        orderMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public Order get(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Order> list() {
        OrderExample example =new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> result =orderMapper.selectByExample(example);
        setUser(result);
        return result;
    }


    @Override
    public List list(int uid, String excludedStatus) {
        OrderExample example =new OrderExample();
        example.createCriteria().andUidEqualTo(uid).andStatusNotEqualTo(excludedStatus);
        example.setOrderByClause("id desc");
        return orderMapper.selectByExample(example);
    }



    private void setUser(List<Order> os) {
        for (Order o : os)
            setUser(o);
    }

    public void setUser(Order o){
        int uid = o.getUid();
        User u = userService.get(uid);
        o.setUser(u);
    }
}
