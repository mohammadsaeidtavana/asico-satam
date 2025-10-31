package com.asico.hr.service;

import com.asico.hr.domain.UserModel;
import com.asico.hr.domain.person.UserProfile;
import com.asico.hr.entity.person.UserProfileEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
public interface UserProfileService {

    UserProfileEntity search(String phoneNumber);

    UserProfileEntity searchByNationalCode(String nationalCode);

    List<UserProfileEntity> getAll();

    int delete(UserProfile model);

    UserProfileEntity save(UserProfile model);

    CompletableFuture<UserProfileEntity> saveAsync(UserProfile model);

    UserProfileEntity update(UserProfile model);



}
