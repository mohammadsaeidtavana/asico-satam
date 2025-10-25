package com.asico.hr.service.impl;


import com.asico.hr.domain.ExamEntity;
import com.asico.hr.domain.ExamModel;
import com.asico.hr.domain.config.MapperUtil;
import com.asico.hr.service.ExamService;
import com.asico.hr.repository.ExamRepository;
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
public class ExamServiceimpl implements ExamService {

    ExamRepository repository;

    public ExamServiceimpl(ExamRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ExamModel> search(String phoneNumber) {
        return MapperUtil.mapList(repository.findByPhoneNumber(phoneNumber), ExamModel.class);
    }

    @Override
    public ExamModel save(ExamModel examModel) {
        return null;
    }

    @Override
    public List<ExamModel> getAll() {
        return MapperUtil.mapList(repository.findAll(), ExamModel.class);
    }

    @Override
    public ExamModel delete(String phoneNumber, String courseId) {
        return null;
    }

    @Override
    public int countNumberOfCertificate(String phoneNumber) {
        List<ExamModel> list = search(phoneNumber);
        return list.size();
    }


    @Override
    public ExamModel searchPhoneNumberAndCourse(String phoneNumber, String CourseId) {
        return MapperUtil.map(repository.findByPhoneNumberAndAndCourseId(phoneNumber, CourseId), ExamModel.class);
    }

    @Async
    @Override
    public CompletableFuture<ExamModel> saveAsync(ExamModel examModel) {

        repository.save(MapperUtil.map(examModel, ExamEntity.class));
        return CompletableFuture.completedFuture(examModel);
    }
}
