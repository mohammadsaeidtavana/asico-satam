package com.asico.hr.service;

import com.asico.hr.domain.ExamModel;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
public interface ExamService {

    List<ExamModel> search(String phoneNumber);

    ExamModel searchPhoneNumberAndCourse(String phoneNumber , String CourseId);

    ExamModel save(ExamModel examModel);

    List<ExamModel> getAll();

    ExamModel delete(String phoneNumber,String courseId);

    int countNumberOfCertificate(String phoneNumber);

    CompletableFuture<ExamModel> saveAsync(ExamModel examModel);


}
