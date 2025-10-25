package com.asico.hr.payment.service.impl;

import com.asico.hr.domain.config.MapperUtil;
import com.asico.hr.payment.domain.OrderEntity;
import com.asico.hr.payment.domain.OrderModel;
import com.asico.hr.payment.repository.OrderRepository;
import com.asico.hr.payment.service.OrderService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */

@Service
public class OrderServiceImpl implements OrderService {


    OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderModel> getall() {

        return MapperUtil.mapList(orderRepository.findAll(),OrderModel.class);
    }

    @Override
    public List<OrderModel> userOrder(String phoneNumber) {
        return MapperUtil.mapList(orderRepository.findByPhoneNumber(phoneNumber),OrderModel.class);
    }

    @Async
    @Override
    public CompletableFuture<OrderModel> save(OrderModel model) {

        orderRepository.save(MapperUtil.map(model, OrderEntity.class));

        return CompletableFuture.completedFuture(model);
    }
}
