package com.asico.hr.controller;

import com.asico.hr.domain.UserModel;
import com.asico.hr.domain.positionRequest;
import com.asico.hr.repository.*;
import com.asico.hr.service.PositionService;
import com.asico.hr.service.UserService;
import com.asico.hr.sms.service.SmsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */
@Controller
@RequestMapping("/v1/user")
public class UserController {


    UserService userService;

    UserLogRepository userLogRepository;

    UserCourseRepository courseRepository;

    ExamRepository examRepository;

    ContactusRepository contactusRepository;

    CertificateRepository certificateRepository;

    CartRepository cartRepository;
    BulletinRepository bulletinRepository;

    PositionService positionService;

    SmsService smsService;


    public UserController(UserService userService, UserLogRepository userLogRepository,
                          UserCourseRepository courseRepository,
                          ExamRepository examRepository, ContactusRepository contactusRepository,
                          CertificateRepository certificateRepository, CartRepository cartRepository,
                          BulletinRepository bulletinRepository, PositionService positionService, SmsService smsService) {
        this.userService = userService;
        this.userLogRepository = userLogRepository;
        this.courseRepository = courseRepository;
        this.examRepository = examRepository;
        this.contactusRepository = contactusRepository;
        this.certificateRepository = certificateRepository;
        this.cartRepository = cartRepository;
        this.bulletinRepository = bulletinRepository;
        this.positionService = positionService;
        this.smsService = smsService;
    }

    @PostMapping(value = "/edit")
    public ModelAndView editUser(@RequestParam("email2") String email, @RequestParam("fullname") String name,
                                 @RequestParam(value = "codemeli", required = false) String codemeli,
                                 @RequestParam(value = "codeshenasname", required = false) String codeshenasname,
                                 @RequestParam(value = "fathername", required = false) String fathername,
                                 @RequestParam(value = "BDplace", required = false) String BDplace,
                                 @RequestParam(value = "bdyear", required = false) String bdyear,
                                 @RequestParam(value = "bdmonth", required = false) String bdmonth,
                                 @RequestParam(value = "bdday", required = false) String bdday,
                                 @RequestParam(value = "issuyear", required = false) String issuyear,
                                 @RequestParam(value = "issumonth", required = false) String issumonth,
                                 @RequestParam(value = "issuday", required = false) String issuday,
                                 @RequestParam(value = "military", required = false) String military,
                                 @RequestParam(value = "ostan", required = false) String ostan,
                                 @RequestParam(value = "city", required = false) String city,
                                 @RequestParam(value = "Address", required = false) String Address,
                                 @RequestParam(value = "bio", required = false) String bio,
                                 @RequestParam(value = "post", required = false) String post,
                                 @RequestParam(value = "nationality", required = false) String nationality,
                                 @RequestParam(value = "religion", required = false) String religion,
                                 @RequestParam(value = "telegram", required = false) String telegram,
                                 HttpServletRequest request, HttpSession httpSession) {

        String phoneNumber = String.valueOf(httpSession.getAttribute("phoneNumber"));
        UserModel userModel = (UserModel) httpSession.getAttribute("userinfo");
        String nationalCode = String.valueOf(httpSession.getAttribute("codeMeli"));

        userModel.setName(name);
        userModel.setEmail(email);
        userModel.setCodemeli(codemeli);
        userModel.setCodeshenasname(codeshenasname);
        userModel.setFathername(fathername);
        userModel.setBDplace(BDplace);
        userModel.setBdate_day(bdday);
        userModel.setBdate_year(bdyear);
        userModel.setBdate_month(bdmonth);
        userModel.setIssuyear(issuyear);
        userModel.setIssumonth(issumonth);
        userModel.setIssuday(issuday);
        userModel.setMilitary(military);
        userModel.setOstan(ostan);
        userModel.setCity(city);
        userModel.setAddress(Address);
        userModel.setBio(bio);
        userModel.setPost(post);
        userModel.setNationality(nationality);
        userModel.setReligion(religion);
        userModel.setTelegram(telegram);
        userService.update(userModel);


        ModelAndView view = new ModelAndView("redirect:/profile-edit");
        return view;

    }

    @PostMapping(value = "/position")
    public ModelAndView time(@RequestParam("position") String position, @RequestParam("companyName") String company,
                             HttpServletRequest request, HttpSession httpSession) {

        System.out.println(position);
        System.out.println(company);

        try {

            String phoneNumber = String.valueOf(httpSession.getAttribute("phoneNumber"));
            String nationalCode = String.valueOf(httpSession.getAttribute("codeMeli"));
            UserModel userModel = (UserModel) httpSession.getAttribute("userinfo");
            positionRequest positionRequest = new positionRequest();
            List<positionRequest> positionRequestList = positionService.search(phoneNumber);
            System.out.println(positionRequestList.toString());
            if (positionRequestList.isEmpty() || positionRequestList.size() == 0) {

                System.out.println("===== positionRequestList null ");
                positionRequest.setPhoneNumber(phoneNumber);
                positionRequest.setPosition(position);
                positionRequest.setCompany(company);
                positionRequest.setProcess(false);
                positionRequest.setNationalCode(nationalCode);
                positionRequest.setDate(new Date());
                positionService.save(positionRequest);
                smsService.sendWelcomeCourseSmsAsync(phoneNumber, userModel.getName());
                ModelAndView view = new ModelAndView("redirect:/profile");
                return view;
                // redirect to success page
            } else {
                System.out.println("===== positionRequestList null111 ");
                for (positionRequest positionRequestObject : positionRequestList) {
                    if (positionRequestObject.isProcess() == true) {
                        positionRequest.setPhoneNumber(phoneNumber);
                        positionRequest.setPosition(position);
                        positionRequest.setCompany(company);
                        positionRequest.setProcess(false);
                        positionRequest.setNationalCode(nationalCode);
                        positionRequest.setDate(new Date());
                        positionService.save(positionRequest);
                        smsService.sendWelcomeCourseSmsAsync(phoneNumber, userModel.getName());
                        ModelAndView view = new ModelAndView("redirect:/profile");
                        return view;
                        // redirect to success page
                    } else {
                        System.out.println("===== positionRequestList nul222l ");
                        ModelAndView view = new ModelAndView("redirect:/panel-error");
                        String errorMessage = "َشما یک درخواست در جریان دارید .  ";
                        view.addObject("errorMessage", errorMessage);
                        return view;
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("===== positionRequestList null exception ");
            ModelAndView view = new ModelAndView("redirect:/panel-error");
            String errorMessage = "َثبت درخواست با خطا مواجه شد ، لطفا دقایقی دیگر تلاش کنید .  ";
            view.addObject("errorMessage", errorMessage);
            return view;
        }
        System.out.println("===== positionRequestList full ");
        ModelAndView view = new ModelAndView("redirect:/profile");
        return view;
    }

    @PostMapping(value = "/editPhone")
    public ModelAndView edit_phone(@RequestParam("phone_number") String oldPhone, @RequestParam("phone_number_new") String newPhone,
                                   HttpServletRequest request, HttpSession httpSession) {

        userService.updatephone(oldPhone, newPhone);

        userLogRepository.updatePhoneNumber(oldPhone, newPhone);

        courseRepository.updatePhoneNumber(oldPhone, newPhone);

        examRepository.updatePhoneNumber(oldPhone, newPhone);

        contactusRepository.updatePhoneNumber(oldPhone, newPhone);

        certificateRepository.updatePhoneNumber(oldPhone, newPhone);

        cartRepository.updatePhoneNumber(oldPhone, newPhone);

        bulletinRepository.updatePhoneNumber(oldPhone, newPhone);

        ModelAndView view = new ModelAndView("redirect:/logout");
        return view;

    }


}