package com.asico.hr.controller;

import com.asico.hr.domain.UserModel;
import com.asico.hr.domain.studyEntity;
import com.asico.hr.repository.*;
import com.asico.hr.service.StudyService;
import com.asico.hr.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    StudyService studyService;


    public UserController(UserService userService, UserLogRepository userLogRepository,
                          UserCourseRepository courseRepository,
                          ExamRepository examRepository, ContactusRepository contactusRepository,
                          CertificateRepository certificateRepository, CartRepository cartRepository,
                          BulletinRepository bulletinRepository, StudyService studyService) {
        this.userService = userService;
        this.userLogRepository = userLogRepository;
        this.courseRepository = courseRepository;
        this.examRepository = examRepository;
        this.contactusRepository = contactusRepository;
        this.certificateRepository = certificateRepository;
        this.cartRepository = cartRepository;
        this.bulletinRepository = bulletinRepository;
        this.studyService = studyService;
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

    @PostMapping(value = "/time")
    public ModelAndView time(@RequestParam("time") String time, @RequestParam("subject") String subject,
                             @RequestParam(value = "year", required = false) String year,
                             @RequestParam(value = "month", required = false) String month,
                             @RequestParam(value = "day", required = false) String day,
                             @RequestParam(value = "type", required = false) String type,
                             HttpServletRequest request, HttpSession httpSession) {


        String phoneNumber = String.valueOf(httpSession.getAttribute("phoneNumber"));
        UserModel userModel = (UserModel) httpSession.getAttribute("userinfo");

        studyEntity studyEntity = new studyEntity();
        if (userModel==null){
            studyEntity.setName("");

        }else {

            studyEntity.setName(userModel.getName());

        }

        studyEntity.setPhoneNumber(phoneNumber);
        studyEntity.setYear(year);
        studyEntity.setMonth(month);
        studyEntity.setDay(day);
        studyEntity.setDuration(Long.parseLong(time));
        studyEntity.setSubject(subject);
        studyEntity.setName(userModel.getName());
        studyService.save(studyEntity);



        ModelAndView view = new ModelAndView("redirect:/read-report");
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