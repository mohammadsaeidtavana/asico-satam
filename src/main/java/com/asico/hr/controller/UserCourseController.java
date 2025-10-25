package com.asico.hr.controller;

import com.asico.hr.service.UserCourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */
@Controller
@RequestMapping("/v1/user/course")
public class UserCourseController {

    UserCourseService userCourseService;

    public UserCourseController(UserCourseService userCourseService) {
        this.userCourseService = userCourseService;
    }


    @PostMapping(value = "/getall")
    public ModelAndView getUserCourse(@RequestParam String phoneNumber) {

        ModelAndView view = new ModelAndView("redirect:/profile-courses");

        return view;

    }


}