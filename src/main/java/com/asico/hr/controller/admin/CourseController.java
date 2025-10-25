package com.asico.hr.controller.admin;

import com.asico.hr.domain.CourseModel;
import com.asico.hr.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */
@RestController
@RequestMapping("/v1/course")
public class CourseController {


    CourseService courseService;


    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity addSystemcCourse(@RequestBody CourseModel course) {

        courseService.saveAsync(course);

        return new ResponseEntity(course, HttpStatus.OK);

    }




}