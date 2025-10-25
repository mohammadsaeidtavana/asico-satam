package com.asico.hr.payment.service;

import com.asico.hr.payment.domain.OrderModel;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */
public interface OrderService {


    List<OrderModel> getall();

    List<OrderModel> userOrder(String phoneNumber);

    CompletableFuture<OrderModel> save(OrderModel model);

}
