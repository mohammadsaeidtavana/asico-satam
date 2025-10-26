package com.asico.hr.web;


import com.asico.hr.domain.CertificateModel;
import com.asico.hr.domain.CourseModel;
import com.asico.hr.domain.ExamModel;
import com.asico.hr.domain.UserCourseModel;
import com.asico.hr.payment.domain.OrderModel;
import com.asico.hr.payment.service.OrderService;
import com.asico.hr.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


/**
 * @author mohammad saeid tavana
 * @since 2024
 */
@Controller
public class UserProfileController {


    UserLogService userLogService;

    UserCourseService userCourseService;

    CourseService courseService;

    CertificateService certificateService;

    ExamService examService;

    OrderService orderService;


    public UserProfileController(UserLogService userLogService, UserCourseService userCourseService, CourseService courseService,
                                 CertificateService certificateService, ExamService examService, OrderService orderService) {
        this.userLogService = userLogService;
        this.userCourseService = userCourseService;
        this.courseService = courseService;
        this.certificateService = certificateService;
        this.examService = examService;
        this.orderService = orderService;
    }

    private final String baseDirectory = "profile/";

    @RequestMapping(value = "/userpanle")
    public String panel(Model model) {

        model.addAttribute("courses", courseService.getAll());
        return baseDirectory + "userpanle";
    }

    @RequestMapping(value = "/profile")
    public String profile(HttpSession httpSession, Model model) {

        String phoneNumber = String.valueOf(httpSession.getAttribute("phoneNumber"));

        int certificateNumber = certificateService.countNumberOfCertificateNotIssue(phoneNumber);
        int examCount = examService.countNumberOfCertificate(phoneNumber);


        List<CertificateModel> certificateList = certificateService.search(phoneNumber);
        List<ExamModel> examList = examService.search(phoneNumber);

        httpSession.setAttribute("examCount", examCount);
        httpSession.setAttribute("certificateCount", certificateNumber);
        httpSession.setAttribute("UserCoursesCount", UserCoursesCount(phoneNumber));
        List<UserCourseModel> list = UserCourses(phoneNumber);
        httpSession.setAttribute("userCourses", list);
        List<CourseModel> detailsList = new ArrayList<>();
        list.forEach(courseModel -> {
            detailsList.add(courseService.search(courseModel.getCourseId()));
        });

        httpSession.setAttribute("certificateList", certificateList);
        model.addAttribute("certificateList", certificateList);

        httpSession.setAttribute("userCoursesDetails", detailsList);
        model.addAttribute("userCoursesDetails", detailsList);
        httpSession.setAttribute("examList", examList);
        model.addAttribute("examList", examList);

        return baseDirectory + "profile";
    }

    @RequestMapping(value = "/profile-courses")
    public String profileCourses(HttpSession httpSession, Model model) {

        String phoneNumber = String.valueOf(httpSession.getAttribute("phoneNumber"));
        List<ExamModel> examList = examService.search(phoneNumber);
        List<CertificateModel> certificateList = certificateService.search(phoneNumber);

        List<UserCourseModel> list = UserCourses(phoneNumber);
        httpSession.setAttribute("userCourses", list);
        List<CourseModel> detailsList = new ArrayList<>();
        list.forEach(courseModel -> {
            detailsList.add(courseService.search(courseModel.getCourseId()));
        });

        httpSession.setAttribute("certificateList", certificateList);
        model.addAttribute("certificateList", certificateList);
        httpSession.setAttribute("userCoursesDetails", detailsList);
        httpSession.setAttribute("examList", examList);
        model.addAttribute("userCoursesDetails", detailsList);
        model.addAttribute("examList", examList);

        return baseDirectory + "profile-courses";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("otpPhone");
        httpSession.removeAttribute("phoneNumber");
        httpSession.removeAttribute("userinfo");
        httpSession.removeAttribute("userCartsCount");
        httpSession.removeAttribute("userCourses");
        httpSession.removeAttribute("userCoursesDetails");
        httpSession.removeAttribute("UserCoursesCount");
        httpSession.removeAttribute("certificateCount");
        httpSession.removeAttribute("examCount");
        httpSession.removeAttribute("examList");
        httpSession.removeAttribute("certificateList");

        return "redirect:/home";
    }

    @RequestMapping(value = "/profile-financial")
    public String profile_financial(HttpSession httpSession , Model model) {

        String phoneNumber = String.valueOf(httpSession.getAttribute("phoneNumber"));

        List<OrderModel> UserOrders=orderService.userOrder(phoneNumber);
        model.addAttribute("userOrders",UserOrders);

        return baseDirectory + "profile-financial";
    }

    @RequestMapping(value = "/profile-comments")
    public String profile_comments(HttpSession httpSession) {

        return baseDirectory + "profile-comments";
    }

    @RequestMapping(value = "/profile-wishlist")
    public String profile_wishlist(HttpSession httpSession) {

        return baseDirectory + "profile-wishlist";
    }

    @RequestMapping(value = "/profile-notifications")
    public String profile_notifications(HttpSession httpSession, Model model) {

        String phoneNumber = String.valueOf(httpSession.getAttribute("phoneNumber"));
        httpSession.setAttribute("logs", userLogService.getFirst5(phoneNumber));
        model.addAttribute("logs", userLogService.getFirst5(phoneNumber));
        return baseDirectory + "profile-notifications";
    }

    @RequestMapping(value = "/profile-edit")
    public ModelAndView profile_edit(HttpSession httpSession) {

        ModelAndView modelAndView = new ModelAndView(baseDirectory + "profile-edit");

        return modelAndView;
    }

    @RequestMapping(value = "/read-report")
    public ModelAndView read_report(HttpSession httpSession) {

        ModelAndView modelAndView = new ModelAndView(baseDirectory + "reading-report");

        return modelAndView;
    }
    @RequestMapping(value = "/panel-error")
    public ModelAndView payment_error(HttpSession httpSession , @RequestParam(required = false) String errorMessage, Model model) {
        model.addAttribute("errorMessage", errorMessage);
        ModelAndView modelAndView = new ModelAndView(baseDirectory + "panel-error");

        return modelAndView;
    }
    private List<UserCourseModel> UserCourses(String phoneNumber) {


        List<UserCourseModel> list = userCourseService.getAll(phoneNumber);
        return list;
    }

    private int UserCoursesCount(String phoneNumber) {


        List<UserCourseModel> list = userCourseService.getAll(phoneNumber);
        return list.size();
    }


}
