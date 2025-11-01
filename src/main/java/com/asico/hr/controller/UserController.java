package com.asico.hr.controller;

import com.asico.hr.domain.UserModel;
import com.asico.hr.domain.person.UserProfile;
import com.asico.hr.domain.positionRequest;
import com.asico.hr.entity.person.UserProfileEntity;
import com.asico.hr.inquery.domain.shahkar.ShahkarRequestApi;
import com.asico.hr.inquery.domain.shahkar.ShahkarResponseApi;
import com.asico.hr.inquery.service.InqueryService;
import com.asico.hr.repository.*;
import com.asico.hr.service.PositionService;
import com.asico.hr.service.UserProfileService;
import com.asico.hr.service.UserService;
import com.asico.hr.sms.service.SmsService;
import com.asico.hr.utils.UniqueRandomGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */
@Controller
//@SessionAttributes("UserProfile")
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

    InqueryService inqueryService;

    UserProfileService userProfileService;


    public UserController(UserService userService, UserLogRepository userLogRepository,
                          UserCourseRepository courseRepository,
                          ExamRepository examRepository, ContactusRepository contactusRepository,
                          CertificateRepository certificateRepository, CartRepository cartRepository,
                          BulletinRepository bulletinRepository, PositionService positionService, SmsService smsService,
                          InqueryService inqueryService, UserProfileService userProfileService) {
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
        this.inqueryService = inqueryService;
        this.userProfileService = userProfileService;
    }

//    @ModelAttribute("UserProfile")
//    public UserProfile userProfile() {
//        return new UserProfile();
//    }

    // دریافت فرم و ذخیره تغییرات
    @PostMapping("/edit")
    public ModelAndView submitEditForm(
            @Valid @ModelAttribute("UserProfile") UserProfile userProfile,
            BindingResult bindingResult,
            Model model,
            HttpSession httpSession
    ) {
        try {


            if (bindingResult.hasErrors()) {
                System.out.println("====== binding error");
                bindingResult.getAllErrors().forEach(error -> {
                    System.out.println(error.getObjectName() + " : " + error.getDefaultMessage());
                });
                ModelAndView view = new ModelAndView("redirect:/profile-edit");
                return view;
            }
            System.out.println("////////edit form controller");

            System.out.println(userProfile.toString());

            UserProfileEntity profile = userProfileService.searchByNationalCode(userProfile.getCodemeli());
            if (profile == null) {
              //  userProfileService.saveAsync(userProfile);
               userProfileService.save(userProfile);
            } else {
                userProfileService.delete(userProfile);
                userProfileService.save(userProfile);
//                CompletableFuture.runAsync(() -> userProfileService.delete(userProfile))
//                        .thenRunAsync(() -> userProfileService.saveAsync(userProfile));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // موفقیت: redirect به فرم یا صفحه موفقیت
        httpSession.setAttribute("UserProfile", userProfile);
        //ModelAndView view = new ModelAndView("redirect:/profile-edit");
        ModelAndView view = new ModelAndView("redirect:/panel-register?type=profileEdit");
        return view;
    }

    @PostMapping(value = "/edit2")
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
                             @RequestParam("userAccept") String userAccept, @RequestParam("industry") String industry, HttpServletRequest request, HttpSession httpSession) {

        System.out.println(position);
        System.out.println(company);
        System.out.println(userAccept);

        int refcode = UniqueRandomGenerator.generateUnique8Digit();

        try {

            String phoneNumber = String.valueOf(httpSession.getAttribute("phoneNumber"));
            String nationalCode = String.valueOf(httpSession.getAttribute("codeMeli"));
            UserProfileEntity userProfileEntity=userProfileService.searchByNationalCode(nationalCode);
            UserModel userModel = (UserModel) httpSession.getAttribute("userinfo");
            positionRequest positionRequest = new positionRequest();
            List<positionRequest> positionRequestList = positionService.search(phoneNumber);
            System.out.println(positionRequestList.toString());
            if (positionRequestList.isEmpty() || positionRequestList.size() == 0) {

                positionRequest.setPhoneNumber(phoneNumber);
                positionRequest.setPosition(position);
                positionRequest.setCompany(company);
                positionRequest.setProcess(false);
                positionRequest.setNationalCode(nationalCode);
                positionRequest.setDate(new Date());
                positionRequest.setIsUserAccept(userAccept);
                positionRequest.setRefcode(refcode);
                positionRequest.setIndustry(industry);
                positionService.save(positionRequest);
                smsService.sendWelcomeCourseSmsAsync(phoneNumber, userProfileEntity.getFullname(), String.valueOf(refcode));
               // ModelAndView view = new ModelAndView("redirect:/profile");
                ModelAndView view = new ModelAndView("redirect:/panel-register?type=register");
                return view;
                // redirect to success page
            } else {
                for (positionRequest positionRequestObject : positionRequestList) {
                    if (positionRequestObject.isProcess() == true) {
                        positionRequest.setPhoneNumber(phoneNumber);
                        positionRequest.setPosition(position);
                        positionRequest.setCompany(company);
                        positionRequest.setProcess(false);
                        positionRequest.setNationalCode(nationalCode);
                        positionRequest.setDate(new Date());
                        positionService.save(positionRequest);
                        smsService.sendWelcomeCourseSmsAsync(phoneNumber, userProfileEntity.getFullname(), String.valueOf(refcode));
                        //ModelAndView view = new ModelAndView("redirect:/profile");
                        ModelAndView view = new ModelAndView("redirect:/panel-register?type=register");
                        return view;
                        // redirect to success page
                    } else {

                        ModelAndView view = new ModelAndView("redirect:/panel-error");
                        String errorMessage = "َشما یک درخواست در جریان دارید .  ";
                        view.addObject("errorMessage", errorMessage);
                        return view;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ModelAndView view = new ModelAndView("redirect:/panel-error");
            String errorMessage = "َثبت درخواست با خطا مواجه شد ، لطفا دقایقی دیگر تلاش کنید .  ";
            view.addObject("errorMessage", errorMessage);
            return view;
        }
        ModelAndView view = new ModelAndView("redirect:/profile");
        return view;
    }

    @PostMapping(value = "/editPhone")
    public ModelAndView edit_phone(@RequestParam("phone_number") String oldPhone, @RequestParam("phone_number_new") String newPhone,
                                   HttpServletRequest request, HttpSession httpSession) {

        ShahkarRequestApi shahkarRequestApi = new ShahkarRequestApi();
        shahkarRequestApi.setMobileNumber(newPhone);
        shahkarRequestApi.setNationalCode((String) httpSession.getAttribute("codeMeli"));
        ShahkarResponseApi shahkarResponse = null;
        try {
            shahkarResponse = inqueryService.shahkar(shahkarRequestApi);


            if (shahkarResponse.isSuccess() && shahkarResponse.getData().getResult().isMatched()) {


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
            } else {
                ModelAndView view = new ModelAndView("redirect:/panel-error");
                String errorMessage = "َشماره موبایل جدید متعلق به شما نیست و لطفا شماره موبایل متعلق به خودتان را وارد کنید .  ";
                view.addObject("errorMessage", errorMessage);
                return view;
            }
        } catch (Exception e) {
            ModelAndView view = new ModelAndView("redirect:/panel-error");
            String errorMessage = "َخطا ، لطفا مجددا چند دقیقه دیگر اقدام کنید .  ";
            view.addObject("errorMessage", errorMessage);
            return view;
        }
    }


}