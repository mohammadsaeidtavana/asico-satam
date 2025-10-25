package com.asico.hr.service;

import com.asico.hr.domain.CourseModel;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
public interface CourseService {


    CourseModel search(String courseId);

    CourseModel add(CourseModel CourseModel);

    List<CourseModel> getAll();

    CourseModel delete(CourseModel model);

    CompletableFuture<CourseModel> saveAsync(CourseModel model);

    CourseModel findByCourseId(String courseIs);

    CourseModel findByCoursePage(String coursePage);

}
