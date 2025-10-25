package com.asico.hr.service.impl;

import com.asico.hr.domain.CartEntity;
import com.asico.hr.domain.CartModel;
import com.asico.hr.domain.CourseModel;
import com.asico.hr.domain.config.MapperUtil;
import com.asico.hr.repository.CartRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */

@Service
public class CartService implements com.asico.hr.service.CartService {


    CartRepository repository;

    public CartService(CartRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CartModel> search(String phoneNumber) {
        List<CartModel> model = MapperUtil.mapList(repository.findByPhoneNumber(phoneNumber), CartModel.class);
        return model;
    }

    @Override
    public CartModel save(CartModel cartModel) {
        return null;
    }

    @Override
    public List<CartModel> getAll() {
        return null;
    }


    @Override
    public CartModel delete(String phoneNumber, String courseId) {

        CartEntity cart = repository.findByPhoneNumberAndAndCourseId(phoneNumber, courseId);
        repository.delete(cart);

        return MapperUtil.map(cart, CartModel.class);

    }

    @Async
    @Override
    public CompletableFuture<CartModel> saveAsync(CartModel model) {

        CartEntity cartEntity = repository.findByPhoneNumberAndAndCourseId(model.getPhoneNumber(), model.getCourseId());
        if (cartEntity == null) {
            repository.save(MapperUtil.map(model, CartEntity.class));
        }

        return CompletableFuture.completedFuture(model);

    }

    @Override
    public int deleteByCourseId(String courseId) {


        return repository.deleteByCourseId(courseId);
    }

    @Override
    public long calculateTotalPrice(List<CourseModel> list) {

        long totalPrice = list.stream().mapToLong(CourseModel::getPrice).sum();

        return totalPrice;
    }

    @Override
    public long calculateTotalAfterDiscount(List<CourseModel> list) {
        long totalPrice = list.stream().mapToLong(CourseModel::getDiscountPrice).sum();

        return totalPrice;
    }

    @Override
    public long calculateTotalDiscount(List<CourseModel> list) {
        long totalPrice = list.stream().mapToLong(CourseModel::getPrice).sum();

        long totalPriceAfterDiscount = list.stream().mapToLong(CourseModel::getDiscountPrice).sum();

        return totalPrice - totalPriceAfterDiscount;


    }
}
