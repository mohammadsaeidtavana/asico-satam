package com.asico.hr.service;

import com.asico.hr.domain.UserCourseModel;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
public interface UserCourseService {

    List<UserCourseModel> getAll(String phoneNumber);


    CompletableFuture<UserCourseModel> saveAsync(UserCourseModel userCourseModel);

}
