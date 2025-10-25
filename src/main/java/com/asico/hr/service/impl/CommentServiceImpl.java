//package com.greenbox.lms.service.impl;
//
//import com.greenbox.lms.domain.CommentModel;
//import com.greenbox.lms.domain.CourseModel;
//import com.greenbox.lms.repository.CommentRepository;
//import com.greenbox.lms.repository.CourseRepository;
//import com.greenbox.lms.service.CommentService;
//import com.greenbox.lms.service.CourseService;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//
///**
// * @author mohammad saeid tavana
// * @version 1.0
// * @since 2024
// */
//
//@Service
//public class CommentServiceImpl implements CommentService {
//
//
//    CommentRepository commentRepository;
//
//    CourseService courseService;
//
//    public CommentServiceImpl(CommentRepository commentRepository, CourseService courseService) {
//        this.commentRepository = commentRepository;
//        this.courseService = courseService;
//    }
//
//    @Override
//    public CommentModel search(String coursId) {
//        return null;
//    }
//
//    @Override
//    public CommentModel add(CommentModel bulletinModel) {
//        return null;
//    }
//
//    @Override
//    public List<CommentModel> getAll(String coursId) {
//        List<CommentModel> commentModelList = new ArrayList<>();
//        List<CourseModel> list = courseService.getAll();
//        for (CourseModel model : list) {
//
//            if (model.getCourseId().trim().equals(coursId)) {
//
//                commentModelList = model.getComments();
//                return commentModelList;
//            }
//
//        }
//        return commentModelList;
//    }
//
//    @Override
//    public CommentModel delete(CommentModel model) {
//        return null;
//    }
//    @Async
//    @Override
//    public CompletableFuture<CommentModel> saveAsync(CommentModel model, String coursId) {
//
//        List<CommentModel> commentModelList = new ArrayList<>();
//        CourseModel courseModel = courseService.search(coursId);
//        model.setCourse(courseModel);
//        commentModelList = courseModel.getComments();
//        commentModelList.add(model);
//        courseModel.setComments(commentModelList);
//        courseService.saveAsync(courseModel);
//        return CompletableFuture.completedFuture(model);
//    }
//}
