package com.asico.hr.controller;


import com.asico.hr.domain.ExamModel;
import com.asico.hr.service.ExamService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */
@Controller
@RequestMapping("/v1/exam")
public class ExamController {


    ExamService service;

    public ExamController(ExamService service) {
        this.service = service;
    }

    @GetMapping(value = "/add")
    public ModelAndView redirectHome(@RequestParam("courseid") String courseId,
                                     HttpServletRequest request, HttpSession httpSession) {

        ModelAndView view = null;
        String phoneNumber = String.valueOf(httpSession.getAttribute("phoneNumber"));

        try {

            ExamModel examModel = service.searchPhoneNumberAndCourse(phoneNumber, courseId);

            if (examModel == null) {
                ExamModel examModel2 = new ExamModel();
                examModel2.setCourseId(courseId);
                examModel2.setPhoneNumber(phoneNumber);
                examModel2.setScore("---");
                examModel2.setResult("---");
                service.saveAsync(examModel2);

                // send sms to user
                view = new ModelAndView("redirect:/profile-courses");
            } else {


                // show message that " shoma ghablan sabte nam kardeind"
                view = new ModelAndView("redirect:/profile-courses");
            }

        } catch (Exception e) {
            ExamModel examModel2 = new ExamModel();
            examModel2.setCourseId(courseId);
            examModel2.setPhoneNumber(phoneNumber);
            examModel2.setScore("---");
            examModel2.setResult("---");
            service.saveAsync(examModel2);

            // send sms to user
            view = new ModelAndView("redirect:/profile-courses");
        }


        return view;

    }

}
