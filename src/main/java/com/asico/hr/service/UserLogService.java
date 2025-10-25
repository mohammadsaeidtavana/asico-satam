package com.asico.hr.service;

import com.asico.hr.domain.UserLogModel;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
public interface UserLogService {

    UserLogModel search(String phoneNumber);

    UserLogModel add(UserLogModel contactusModel);

    List<UserLogModel> getAll();

    List<UserLogModel> getFirst5(String phoneNumber);

    UserLogModel delete(UserLogModel model);

    CompletableFuture<UserLogModel> saveAsync(UserLogModel model);

}
