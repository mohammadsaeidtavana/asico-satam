package com.asico.hr.service.impl;

import com.asico.hr.domain.UserLogEntity;
import com.asico.hr.domain.UserLogModel;
import com.asico.hr.domain.config.MapperUtil;
import com.asico.hr.service.UserLogService;
import com.asico.hr.repository.UserLogRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */

@Service
public class UserLogServiceImpl implements UserLogService {

    UserLogRepository userLogRepository;

    public UserLogServiceImpl(UserLogRepository userLogRepository) {
        this.userLogRepository = userLogRepository;
    }

    @Override
    public UserLogModel search(String phoneNumber) {
        return null;
    }

    @Override
    public UserLogModel add(UserLogModel contactusModel) {
        return null;
    }

    @Override
    public List<UserLogModel> getAll() {

        List<UserLogModel> list = MapperUtil.mapList(userLogRepository.findAll(), UserLogModel.class);

        return list;
    }

    @Override
    public List<UserLogModel> getFirst5(String phoneNumber) {
        List<UserLogModel> list = MapperUtil.mapList(userLogRepository.findAllByPhoneNumber(phoneNumber), UserLogModel.class);
        List<UserLogModel> firstFiveElements = list.subList(0, Math.min(list.size(), 5));
        Collections.reverse(list);
        return firstFiveElements;
    }

    @Override
    public UserLogModel delete(UserLogModel model) {
        return null;
    }

    @Async
    @Override
    public CompletableFuture<UserLogModel> saveAsync(UserLogModel model) {

        UserLogEntity userLogEntity = MapperUtil.map(model, UserLogEntity.class);
        userLogRepository.save(userLogEntity);
        return CompletableFuture.completedFuture(MapperUtil.map(userLogEntity, UserLogModel.class));
    }
}
