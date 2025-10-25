package com.asico.hr.service.impl;

import com.asico.hr.domain.UserCourseEntity;
import com.asico.hr.domain.UserCourseModel;
import com.asico.hr.domain.config.MapperUtil;
import com.asico.hr.service.UserCourseService;
import com.asico.hr.repository.UserCourseRepository;
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
public class UserCourseServiceImpl implements UserCourseService {


    UserCourseRepository repository;

    public UserCourseServiceImpl(UserCourseRepository repository) {
        this.repository = repository;
    }




    @Override
    public List<UserCourseModel> getAll(String phoneNumber) {

        return MapperUtil.mapList(repository.findByPhoneNumber(phoneNumber),UserCourseModel.class);
    }


    @Async
    @Override
    public CompletableFuture<UserCourseModel> saveAsync(UserCourseModel userCourseModel) {

        repository.save(MapperUtil.map(userCourseModel, UserCourseEntity.class));

        return CompletableFuture.completedFuture(userCourseModel);
    }
}
