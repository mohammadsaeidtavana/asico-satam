package com.asico.hr.service;

import com.asico.hr.domain.CartModel;
import com.asico.hr.domain.CourseModel;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
public interface CartService {

    List<CartModel> search(String phoneNumber);

    CartModel save(CartModel cartModel);

    List<CartModel> getAll();

    CartModel delete(String phoneNumber,String courseId);

    CompletableFuture<CartModel> saveAsync(CartModel model);

    int deleteByCourseId(String courseId);

    long calculateTotalPrice(List<CourseModel> list);

    long calculateTotalAfterDiscount(List<CourseModel> list);


    long calculateTotalDiscount(List<CourseModel> list);

}
