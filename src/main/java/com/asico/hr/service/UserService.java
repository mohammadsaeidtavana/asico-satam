package com.asico.hr.service;

import com.asico.hr.domain.UserModel;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
public interface UserService {

    UserModel search(String phoneNumber);

    UserModel add(UserModel userModel);

    List<UserModel> getAll();

    UserModel delete(UserModel model);

    UserModel save(UserModel model);

    CompletableFuture<UserModel> saveAsync(UserModel model);

    UserModel update(UserModel model);

    int updatephone(String oldPhone  ,String newPhone);

}
