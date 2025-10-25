package com.asico.hr.web;


import com.asico.hr.domain.CourseModel;
import com.asico.hr.domain.UserCourseModel;
import com.asico.hr.service.CartService;
import com.asico.hr.service.CourseService;
import com.asico.hr.service.UserCourseService;
import com.asico.hr.sms.domain.OtpRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


/**
 * @author mohammad saeid tavana
 * @since 2024
 */
@Controller
public class controller {

    CourseService courseService;

    UserCourseService userCourseService;

    CartService cartService;

    public controller(CourseService courseService, UserCourseService userCourseService, CartService cartService) {
        this.courseService = courseService;
        this.userCourseService = userCourseService;
        this.cartService = cartService;
    }

    @RequestMapping(value = "/index")
    public String index(Model model) {

        model.addAttribute("courses", getCourses());
        return "home";
    }

    @RequestMapping(value = "/")
    public String index2(Model model) {
        model.addAttribute("courses", getCourses());
        return "home";
    }

    @RequestMapping(value = "/home")
    public String home(Model model) {
        model.addAttribute("courses", getCourses());
        return "home";
    }

    @RequestMapping(value = "/errorPage")
    public String error() {
        return "error";
    }

    @RequestMapping(value = "/test")
    public String test() {
        return "index1";
    }


    @RequestMapping(value = "/userlogin")
    public String login(Model model) {

        OtpRequest otpRequest = new OtpRequest();
        model.addAttribute("otp", otpRequest);
        return "login-register";
    }

    @RequestMapping(value = "/verification")
    public String verify(Model model, HttpSession httpSession) {

        String phone = (String) httpSession.getAttribute("otpPhone");
        return "verification";
    }

    @RequestMapping(value = "/contact-us")
    public String contact_us(Model model) {

        return "contact-us";
    }

    @RequestMapping(value = "/contact-us-panel")
    public String contact_us_panel(Model model) {

        return "contact-us-panel";
    }

    @RequestMapping(value = "/about_us")
    public String about_us(Model model) {

        return "about-us";
    }

    @RequestMapping(value = "/about-us-panel")
    public String about_us_panel(Model model) {

        return "about-us-panel";
    }

    @RequestMapping(value = "/learning-path")
    public String learning_path(Model model) {

        return "learning-path";
    }


    @RequestMapping(value = "/template")
    public String template(Model model) {

        return "template";
    }

    @RequestMapping(value = "/lecturer")
    public String lecturer(Model model) {

        return "lecturer";
    }

    @RequestMapping(value = "/blog")
    public String blog(Model model) {

        return "blog";
    }

    @RequestMapping(value = "/blog-panel")
    public String blog_panel(Model model) {

        return "blog-panel";
    }

    @RequestMapping(value = "/guide")
    public String guide(Model model) {

        return "guide";
    }

    @RequestMapping(value = "/login-error")
    public String login_error(@RequestParam(required = false) String errorMessage, Model model) {
        model.addAttribute("errorMessage", errorMessage);
        return "login-error";
    }

    @RequestMapping(value = "/series")
    public String series(Model model) {
        model.addAttribute("courses", getCourses());
        return "series";
    }

    @RequestMapping(value = "/series-panel")
    public String seriesPanel(Model model , HttpSession httpSession, RedirectAttributes redirectAttributes) {

        String phoneNumber = String.valueOf(httpSession.getAttribute("phoneNumber"));
        model.addAttribute("userCartsCount", cartService.search(phoneNumber).size());
        httpSession.setAttribute("userCartsCount", cartService.search(phoneNumber).size());
        redirectAttributes.addFlashAttribute("userCartsCount",cartService.search(phoneNumber).size());

        List<UserCourseModel> list = UserCourses(phoneNumber);


        model.addAttribute("courses", getCourses());
        model.addAttribute("userCourses", list);
        return "series-panel";
    }

    private List<UserCourseModel> UserCourses(String phoneNumber) {


        List<UserCourseModel> list = userCourseService.getAll(phoneNumber);
        return list;
    }

    private List<CourseModel> getCourses() {

        List<CourseModel> courseModelList = courseService.getAll();

        return courseModelList;

    }


}
