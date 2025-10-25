package com.asico.hr.service.impl;

import com.asico.hr.domain.CourseEntity;
import com.asico.hr.domain.CourseModel;
import com.asico.hr.domain.config.MapperUtil;
import com.asico.hr.service.CourseService;
import com.asico.hr.repository.CourseRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */

@Service
public class CourseServiceImpl implements CourseService {


    CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseModel search(String courseId) {

        CourseModel courseModel = null;
        List<CourseModel> list = MapperUtil.mapList(courseRepository.findAll(), CourseModel.class);
        for (CourseModel model : list) {
            if (model.getCourseId().trim().equals(courseId)) {

                courseModel = model;
            }

        }
        return courseModel;
    }

    @Override
    public CourseModel add(CourseModel CourseModel) {
        return null;
    }

    @Override
    public List<CourseModel> getAll() {
        ArrayList<CourseModel> finalList = new ArrayList<CourseModel>();
        List<CourseModel> list = MapperUtil.mapList(courseRepository.findAll(), CourseModel.class);
        list.forEach(a-> {
            if (a.isCourseState()) {
                finalList.add(a);
            }
        });

        return finalList;
    }

    @Override
    public CourseModel delete(CourseModel model) {
        return null;
    }


    @Async
    @Override
    public CompletableFuture<CourseModel> saveAsync(CourseModel model) {
        courseRepository.save(MapperUtil.map(model, CourseEntity.class));
        return CompletableFuture.completedFuture(model);
    }

    @Override
    public CourseModel findByCourseId(String courseIs) {


        return MapperUtil.map(courseRepository.findByCourseId(courseIs), CourseModel.class);
    }

    @Override
    public CourseModel findByCoursePage(String coursePage) {


        return MapperUtil.map(courseRepository.findByCoursePage(coursePage), CourseModel.class);
    }
}
