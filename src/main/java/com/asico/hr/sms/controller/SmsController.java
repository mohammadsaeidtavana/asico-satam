package com.asico.hr.sms.controller;


import com.asico.hr.domain.CartModel;
import com.asico.hr.domain.UserCourseModel;
import com.asico.hr.domain.UserLogModel;
import com.asico.hr.domain.UserModel;
import com.asico.hr.inquery.domain.nationalcode.NationalCodeRequestApi;
import com.asico.hr.inquery.domain.nationalcode.NationalCodeResponseApi;
import com.asico.hr.inquery.domain.shahkar.ShahkarRequestApi;
import com.asico.hr.inquery.domain.shahkar.ShahkarResponseApi;
import com.asico.hr.inquery.service.InqueryService;
import com.asico.hr.service.*;
import com.asico.hr.sms.domain.OtpRequest;
import com.asico.hr.sms.domain.OtpResponse;
import com.asico.hr.sms.service.ISmsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author m.tavana
 * @since 2025\
 */

@Controller
@RequestMapping("${sms.otp.controller.url}")
public class SmsController {

    ISmsService service;

    UserLogService userLogService;

    UserService userService;

    CartService cartService;

    UserCourseService userCourseService;

    CourseService courseService;

    InqueryService inqueryService;

    @Value("${sms.otp.controller.url}")
    String baseUrl;

    private final static String errorMessage = "کد وارد شده معتبر نمی باشد ";

    public SmsController(ISmsService service, UserLogService userLogService, UserService userService, CartService cartService
            , UserCourseService userCourseService, CourseService courseService, InqueryService inqueryService) {
        this.service = service;
        this.userLogService = userLogService;
        this.userService = userService;
        this.cartService = cartService;
        this.userCourseService = userCourseService;
        this.courseService = courseService;
        this.inqueryService = inqueryService;
    }

    @PostMapping(value = "/send")
    public ModelAndView sendCode(@Valid @ModelAttribute("otp") OtpRequest otpRequest, BindingResult result, HttpSession httpSession) throws ExecutionException, InterruptedException {
        ModelAndView modelAndView = null;
        try {
            System.out.println(otpRequest.toString());

            httpSession.setAttribute("codeMeli", otpRequest.getCodeMeli());
            if (result.hasErrors()) {

                String errorMessage = result.getFieldError().getDefaultMessage();
                httpSession.setAttribute("message", errorMessage);
                ModelAndView view = new ModelAndView("redirect:/userlogin");
                // ModelAndView view = new ModelAndView("login");
                return view;
            } else {

                //check code meli
                NationalCodeRequestApi requestApi = new NationalCodeRequestApi();
                requestApi.setNationalCode(otpRequest.getCodeMeli());
                NationalCodeResponseApi nationalCodeResponseApi = inqueryService.nationalCodeCheck(requestApi);
                if (nationalCodeResponseApi.isSuccess() && nationalCodeResponseApi.getData().getResult().isValid()) {

                    //check shahkar
                    ShahkarRequestApi shahkarRequestApi = new ShahkarRequestApi();
                    shahkarRequestApi.setNationalCode(otpRequest.getCodeMeli());
                    shahkarRequestApi.setMobileNumber(otpRequest.getPhoneNumber());
                    ShahkarResponseApi shahkarResponseApi = inqueryService.shahkar(shahkarRequestApi);
                    if (shahkarResponseApi.isSuccess() && shahkarResponseApi.getData().getResult().isMatched()) {

                        //sent sms
                        CompletableFuture<OtpResponse> otpResponseAsync = service.sendCodeAsync(otpRequest);
                        if (otpResponseAsync.get() != null && otpResponseAsync.get().getResult().getCode() == 200) {
                            httpSession.setAttribute("otpPhone", otpRequest.getPhoneNumber());
                            modelAndView = new ModelAndView("redirect:" + baseUrl + "/verification");
                            return modelAndView;
                        } else if (otpResponseAsync.get() != null && otpResponseAsync.get().getResult().getCode() == 400) {
                            System.out.println(" cond has been send wait for 120 second ");
                            httpSession.setAttribute("otpPhone", otpRequest.getPhoneNumber());
                            modelAndView = new ModelAndView("redirect:" + baseUrl + "/verification");
                            return modelAndView;
                        } else {
                            modelAndView = new ModelAndView("redirect:" + baseUrl + "/verification");
                            return modelAndView;

                        }
                    } else {

                        //code meli ba shoare motabeghat nadarat
                        String errorMessage = "کد ملی با شماره موبایل مطابقت ندارد. لطفاً شماره موبایل متعلق به خود را وارد نمایید.";
                        modelAndView = new ModelAndView("redirect:" + "/login-error");
                        modelAndView.addObject("errorMessage", errorMessage);
                        return modelAndView;


                    }
                } else {

                    //invalid national code
                    modelAndView = new ModelAndView("redirect:" + "/login-error");
                    String errorMessage = "َشماره ملی نامعبر می باشد . ";
                    modelAndView.addObject("errorMessage", errorMessage);

                    return modelAndView;
                }

            }
        }catch (Exception e){
            modelAndView = new ModelAndView("redirect:" + "/login-error");
            String errorMessage = "شماره موبایل یا کد ملی اشتباه می باشد. لطفا مقدار درست را وارد کنید  ";
            modelAndView.addObject("errorMessage", errorMessage);
            return modelAndView;
        }
    }

    @PostMapping(value = "/verify")
    public ModelAndView verify(@RequestParam String otpCode, @RequestParam("otpPhone") String otpPhone,
                               HttpSession httpSession, Model model, RedirectAttributes redirectAttributes) {


        httpSession.setAttribute("otpPhone", otpPhone);
        httpSession.setAttribute("phoneNumber", otpPhone);
        ModelAndView modelAndView = null;
        if (otpCode == null || otpCode.equals("") || otpCode == "" || otpCode.length() < 6) {

            modelAndView = new ModelAndView("redirect:" + baseUrl + "/verification");

        }


        try {

            OtpRequest request = new OtpRequest();
            request.setPhoneNumber(otpPhone);

            OtpResponse response = service.verification(request, otpCode);
            if (response.getResult().getCode() == 200) {

                httpSession.setAttribute("userCartsCount", userCartCount(otpPhone));
                httpSession.setAttribute("userCourses", UserCourses(otpPhone));
                httpSession.setAttribute("UserCoursesCount", UserCoursesCount(otpPhone));

                if (userService.search(otpPhone) == null) {
                    UserModel newUser = new UserModel();
                    newUser.setPhoneNumber(otpPhone);
                    newUser.setName(otpPhone);
                    userService.saveAsync(newUser);

                  //  modelAndView = new ModelAndView("redirect:/userpanle");
                    modelAndView = new ModelAndView("redirect:/profile-edit");
//                    modelAndView.addObject("userinfo", newUser);
//                    model.addAttribute("userinfo", newUser);
                    httpSession.setAttribute("userinfo", newUser);


                } else {
                    UserModel userModel = userService.search(otpPhone);
//                    modelAndView = new ModelAndView("redirect:/userpanle");
                    modelAndView = new ModelAndView("redirect:/profile-edit");

                    httpSession.setAttribute("userinfo", userModel);
                }
                //modelAndView = new ModelAndView("redirect:/userpanle");
                modelAndView = new ModelAndView("redirect:/profile-edit");

            } else {

                // add session atribute that the code is invalid
                modelAndView = new ModelAndView("redirect:" + baseUrl + "/verification");
                modelAndView.addObject("errorMessage", errorMessage);
                httpSession.setAttribute("errorMessage", errorMessage);
                redirectAttributes.addFlashAttribute("errorMessage", errorMessage);


            }
            System.out.println(response.toString());
        } catch (Exception e) {
            modelAndView = new ModelAndView("redirect:" + baseUrl + "/verification");
            modelAndView.addObject("errorMessage", errorMessage);
            httpSession.setAttribute("errorMessage", errorMessage);
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

            // e.printStackTrace();
        }
        httpSession.removeAttribute("otpPhone");
        audit(otpPhone);
        return modelAndView;


    }

    @RequestMapping(value = "/resend")
    public ModelAndView resendCode(@RequestParam(name = "phone") String phoneNumber, HttpSession httpSession) {

        OtpResponse response = service.resend(phoneNumber);
        httpSession.setAttribute("otpPhone", phoneNumber);
        httpSession.setAttribute("message", "code hase been sent again ");
        ModelAndView modelAndView = new ModelAndView("redirect:" + baseUrl + "/verification");
        return modelAndView;
    }

    @RequestMapping(value = "/verification")
    public String verify(Model model, HttpSession httpSession) {

        String phone = (String) httpSession.getAttribute("otpPhone");

        return "redirect:/verification";
    }

    private void audit(String phoneNumber) {

        UserLogModel log = new UserLogModel();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        log.setPhoneNumber(phoneNumber);
        log.setDate(formatter.format(date));
        userLogService.saveAsync(log);
    }

    private int userCartCount(String phoneNumber) {


        List<CartModel> carts = cartService.search(phoneNumber);


        if (carts == null || carts.isEmpty()) {
            return 0;
        } else {
            return carts.size();
        }
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
