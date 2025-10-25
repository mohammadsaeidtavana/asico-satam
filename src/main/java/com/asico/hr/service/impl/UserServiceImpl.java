package com.asico.hr.service.impl;

import com.asico.hr.domain.UserEntity;
import com.asico.hr.domain.UserModel;
import com.asico.hr.domain.config.MapperUtil;
import com.asico.hr.service.UserService;
import com.asico.hr.repository.UserRepository;
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
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel search(String phoneNumber) {
        try {
            return MapperUtil.map(userRepository.findByPhoneNumber(phoneNumber), UserModel.class);

        } catch (Exception e) {

            return null;
        }

    }

    @Override
    public UserModel add(UserModel userModel) {
        return null;
    }

    @Override
    public List<UserModel> getAll() {
        return null;
    }

    @Override
    public UserModel delete(UserModel model) {

        UserEntity user = userRepository.findByPhoneNumber(model.getPhoneNumber());
        userRepository.delete(user);
        return model;
    }

    @Async
    @Override
    public CompletableFuture<UserModel> saveAsync(UserModel model) {

        userRepository.save(MapperUtil.map(model, UserEntity.class));
        return CompletableFuture.completedFuture(model);
    }

    @Override
    public UserModel save(UserModel model) {
        userRepository.save(MapperUtil.map(model, UserEntity.class));
        return model;
    }


    @Override
    public UserModel update(UserModel model) {

        delete(model);
        saveAsync(model);
        return model;
    }

    @Override
    public int updatephone(String oldPhone, String newPhone) {

        try {
            return userRepository.updatePhoneNumber(oldPhone, newPhone);

        } catch (Exception e) {

            return 0;
        }
    }
}
