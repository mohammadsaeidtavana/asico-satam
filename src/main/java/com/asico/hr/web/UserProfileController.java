package com.asico.hr.web;


import com.asico.hr.domain.*;
import com.asico.hr.domain.person.UserProfile;
import com.asico.hr.entity.person.UserProfileEntity;
import com.asico.hr.payment.domain.OrderModel;
import com.asico.hr.payment.service.OrderService;
import com.asico.hr.repository.PositonRequestRepository;
import com.asico.hr.service.*;
import com.asico.hr.utils.UserProfileMapper;
import com.github.mfathi91.time.PersianDate;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${default.avatar.image}")
    private String imageUrl;

    UserLogService userLogService;

    UserCourseService userCourseService;

    CourseService courseService;

    CertificateService certificateService;

    ExamService examService;

    OrderService orderService;

    PositionService positionService;

    UserService userService;

    UserProfileService userProfileService;

    UserProfileMapper userProfileMapper;


    public UserProfileController(UserLogService userLogService, UserCourseService userCourseService, CourseService courseService,
                                 CertificateService certificateService, ExamService examService, OrderService orderService,
                                 PositionService positionService, UserService userService, UserProfileService userProfileService,
                                 UserProfileMapper userProfileMapper) {
        this.userLogService = userLogService;
        this.userCourseService = userCourseService;
        this.courseService = courseService;
        this.certificateService = certificateService;
        this.examService = examService;
        this.orderService = orderService;
        this.positionService = positionService;
        this.userService = userService;
        this.userProfileService = userProfileService;
        this.userProfileMapper = userProfileMapper;
    }


    private final String baseDirectory = "profile/";

    @RequestMapping(value = "/userpanle")
    public ModelAndView panel(Model model ,HttpSession httpSession) {

        String codeMeli = (String) httpSession.getAttribute("codeMeli");
        UserProfileEntity userProfileEntity = userProfileService.searchByNationalCode(codeMeli);

        ModelAndView modelAndView = new ModelAndView(baseDirectory + "userpanle");
        httpSession.setAttribute("UserProfile", userProfileEntity);
        modelAndView.addObject("UserProfile", userProfileEntity);
        //return baseDirectory + "userpanle";
        return modelAndView;
    }

    @RequestMapping(value = "/profile")
    public ModelAndView profile(HttpSession httpSession, Model model) {
        ModelAndView modelAndView = new ModelAndView(baseDirectory + "profile");
        String phoneNumber = String.valueOf(httpSession.getAttribute("phoneNumber"));

        String codeMeli = (String) httpSession.getAttribute("codeMeli");
        UserProfileEntity userProfileEntity = userProfileService.searchByNationalCode(codeMeli);
        if (userProfileEntity == null) {
            UserProfileEntity userProfile = new UserProfileEntity();
            userProfile.setCodemeli(codeMeli);
            userProfile.setFullname("'کاربر سامانه");
            userProfile.setPicture(imageUrl);
            httpSession.setAttribute("UserProfile", userProfile);
            modelAndView.addObject("UserProfile", userProfile);
        } else {
            if (userProfileEntity.getPicture() == null) {
                userProfileEntity.setPicture(imageUrl);
            }
            httpSession.setAttribute("UserProfile", userProfileEntity);
            modelAndView.addObject("UserProfile", userProfileEntity);
        }


        int certificateNumber = certificateService.countNumberOfCertificateNotIssue(phoneNumber);
        int examCount = examService.countNumberOfCertificate(phoneNumber);


        List<CertificateModel> certificateList = certificateService.search(phoneNumber);
        List<ExamModel> examList = examService.search(phoneNumber);

        httpSession.setAttribute("examCount", examCount);
        httpSession.setAttribute("certificateCount", certificateNumber);
        httpSession.setAttribute("UserCoursesCount", UserCoursesCount(phoneNumber));
        httpSession.setAttribute("UserRequestCount", UserRequestCunt(phoneNumber));
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

        return modelAndView;
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
        httpSession.removeAttribute("codeMeli");
        httpSession.removeAttribute("UserRequestCount");
        httpSession.removeAttribute("User");

        return "redirect:/home";
    }

    @RequestMapping(value = "/profile-financial")
    public ModelAndView profile_financial(HttpSession httpSession, Model model) {

        ModelAndView modelAndView = new ModelAndView(baseDirectory + "profile-financial");

        String phoneNumber = String.valueOf(httpSession.getAttribute("phoneNumber"));
        httpSession.setAttribute("logs", userLogService.getFirst5(phoneNumber));
        model.addAttribute("logs", userLogService.getFirst5(phoneNumber));

        String codeMeli = (String) httpSession.getAttribute("codeMeli");


        String fullName = "-------";
        String fatherName = "-------";
        String nationalId = (String) httpSession.getAttribute("codeMeli");
        String companyName = "-------";
        String representedCompany = "شرکت سرمایه گذاری آتیه صبا";
        PersianDate today = PersianDate.now();
        String date = today.toString();

        /////////
        UserProfileEntity userProfileEntity = userProfileService.searchByNationalCode(codeMeli);
        if (userProfileEntity == null) {
            UserProfileEntity userProfile = new UserProfileEntity();
            userProfile.setCodemeli(codeMeli);
            userProfile.setFullname("کاربر سامانه");
            userProfile.setPicture(imageUrl);
            httpSession.setAttribute("UserProfile", userProfile);
            modelAndView.addObject("UserProfile", userProfile);
        } else {
            if (userProfileEntity.getPicture() == null) {
                userProfileEntity.setPicture(imageUrl);
            }
            fullName = userProfileEntity.getFullname();
            fatherName = userProfileEntity.getFathername();
            nationalId = userProfileEntity.getCodemeli();
            httpSession.setAttribute("UserProfile", userProfileEntity);
            modelAndView.addObject("UserProfile", userProfileEntity);
        }

        //////////
//        if (userProfileEntity != null) {
//            fullName = userProfileEntity.getFullname();
//            fatherName = userProfileEntity.getFathername();
//            nationalId = userProfileEntity.getCodemeli();
//            companyName = "-------";
//            UserProfileEntity userProfile = new UserProfileEntity();
//            userProfile.setCodemeli(codeMeli);
//            userProfile.setFullname("");
//            userProfile.setPicture(imageUrl);
//            httpSession.setAttribute("UserProfile", userProfile);
//            modelAndView.addObject("UserProfile", userProfile);
//        }else {
//            httpSession.setAttribute("UserProfile", userProfileEntity);
//            modelAndView.addObject("UserProfile", userProfileEntity);
//        }


        String agreementText = String.format("""
                اقرارنامه و تعهدنامه عضو هیات مدیره
                متعهد: آقای %s فرزند %s با شماره ملی %s
                متعهدله: 1- صندوق بازنشستگی کشوری  2- شرکت سرمایه‌گذاری آتیه صبا
                موضوع تعهد: متعهد فوق جناب آقای %s گزینه عضویت در هیات مدیره شرکت %s به نمایندگی از شرکت اصیل %s ، در تاریخ %s با حضور در .................................................. ضمن اقرار و تعهد به موارد زیر اعلام داشتند:
                1- اینجانب دارای تابعیت کشور جمهوری اسلامی ایران می‌باشم.
                2- اینجانب هیچگونه بدهی جاری به بانک‌ها و مؤسسات مالی و اعتباری اعم از خصوصی یا دولتی ندارم.
                3- اینجانب هیچگونه سابقه چک برگشتی رفع سوء اثر نشده ندارم.
                4- اینجانب هیچگونه سابقه محکومیت کیفری مؤثر ندارم.
                5- اینجانب هیچگونه ممنوعیت و محدودیتی از جمله ممنوعیت‌های ناظر بر تصدی سمت‌های مدیریتی مقرر در قوانین و مقررات مربوط از جمله اصل (141) قانون اساسی جمهوری اسلامی ایران، قانون ممنوعیت تصدی بیش از یک شغل مصوب 1373، تبصره (2) ماده (241) و مواد (111) و (126) لایحه اصلاح قانون تجارت مصوب 1347 با اصلاحات بعدی را ندارم.
                6- اینجانب بازنشسته و یا بازخرید خدمتی نمی‌باشم.
                7- تأیید می‌نمایم که از نظر صلاحیت عمومی، وثاقت، امانت، واجد شرایط لازم بوده و هیچگونه منع یا مشکلی از سوی مراجع قانونی ذیصلاح در این خصوص ندارم.
                8- اینجانب موضوع ماده (28) قانون ارتقاء سلامت نظام اداری و مقابله با فساد مصوب 1387 با اصلاحات و الحاقات بعدی را می‌پذیرم و اعلام می‌نمایم طی دو سال گذشته هیچگونه مسئولیت یا سمت نظارتی در دستگاه‌های عضو شورای هماهنگی دستگاه‌های نظارتی نداشته‌ام.
                9- اینجانب اصالتاً یا به نمایندگی از اشخاص حقوقی در هیچ شرکت یا مؤسسه دیگری اعم از خصوصی، دولتی یا عمومی غیردولتی مدیرعامل یا عضو هیات مدیره نیستم.
                10- اینجانب هیچگونه محکومیت انضباطی موضوع قوانین و مقررات بازار سرمایه ندارم و مشمول هیچ‌یک از ممنوعیت‌های قانونی از جمله ممنوعیت‌ها و محدودیت‌های مقررات قانون تجارت، بورس اوراق بهادار، مفاد دستورالعمل حاکمیت شرکتی از جمله ماده 4 دستورالعمل مذکور و ... نیستم.
                11- اینجانب متعهد می‌شوم در دوره تصدی عضویت در هیات مدیره شرکت %s کلیه قوانین و مقررات موضوعه کشور اعم از قانون تجارت، مقررات بورس، مصوبات ارکان تصمیم‌گیر صندوق بازنشستگی کشوری و شرکت هلدینگ را رعایت و مد نظر و عمل قرار دهم.
                12- اینجانب متعهد می‌شوم در تمامی جلسات، اظهارنظرها، تصمیمات و اقدامات در شرکت را بر اساس نظر و با هماهنگی شرکت اصیل اعمال نمایم به نحوی که تصمیمات و اعلام نظرهای مؤثر اینجانب در شرکت، با نظر و موضع شرکت اصیل مغایرت نداشته باشد.
                13- در صورت عدم ایفای تعهدات و یا بروز موارد خلاف واقع، اینجانب در کمال صحت و سلامت عقل و با اختیار ملزم به جبران خسارات وارده می‌باشم.

                نام و نام خانوادگی: %s
                امضاء:
                """, fullName, fatherName, nationalId, fullName, companyName, representedCompany, date, companyName, fullName);

        httpSession.setAttribute("agreementText", agreementText);


//        List<OrderModel> UserOrders = orderService.userOrder(phoneNumber);
//        model.addAttribute("userOrders", UserOrders);

        return modelAndView;
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
    public ModelAndView profile_notifications(HttpSession httpSession, Model model) {
        ModelAndView modelAndView = new ModelAndView(baseDirectory + "profile-notifications");
        String codeMeli = (String) httpSession.getAttribute("codeMeli");
        UserProfileEntity userProfileEntity = userProfileService.searchByNationalCode(codeMeli);
        if (userProfileEntity == null) {
            UserProfileEntity userProfile = new UserProfileEntity();
            userProfile.setCodemeli(codeMeli);
            userProfile.setFullname("");
            userProfile.setPicture(imageUrl);
            httpSession.setAttribute("UserProfile", userProfile);
            modelAndView.addObject("UserProfile", userProfile);
        } else {
            if (userProfileEntity.getPicture() == null) {
                userProfileEntity.setPicture(imageUrl);
            }
            httpSession.setAttribute("UserProfile", userProfileEntity);
            modelAndView.addObject("UserProfile", userProfileEntity);
        }

        String phoneNumber = String.valueOf(httpSession.getAttribute("phoneNumber"));
        httpSession.setAttribute("logs", userLogService.getFirst5(phoneNumber));
        model.addAttribute("logs", userLogService.getFirst5(phoneNumber));
        // return baseDirectory + "profile-notifications";
        return modelAndView;
    }

    @RequestMapping(value = "/profile-edit")
    public ModelAndView profile_edit(HttpSession httpSession) {

        System.out.println("/////profile-edit");

        String codeMeli = (String) httpSession.getAttribute("codeMeli");
        ModelAndView modelAndView = new ModelAndView(baseDirectory + "profile-edit");
        // اضافه کردن شیء UserProfile به مدل

        UserProfileEntity userProfileEntity = userProfileService.searchByNationalCode(codeMeli);
        UserProfile profile = new UserProfile();
        if (userProfileEntity == null) {

            profile.setCodemeli(codeMeli);
            profile.setFullname("کاربر سامانه");
            httpSession.setAttribute("ProfilePicture", imageUrl);
            httpSession.setAttribute("UserProfile", profile);
            modelAndView.addObject("UserProfile", profile);

        } else {
            UserProfile userProfile = userProfileMapper.entityToDto(userProfileEntity);
            if (userProfileEntity.getPicture() == null) {
                userProfileEntity.setPicture(imageUrl);
            }
            httpSession.setAttribute("ProfilePicture", userProfileEntity.getPicture());
            httpSession.setAttribute("UserProfile", userProfile);
            modelAndView.addObject("UserProfile", userProfile);
        }


//        UserProfile profile=new UserProfile();
//        profile.setCodemeli(codeMeli);
//        profile.setFullname("");
//        httpSession.setAttribute("UserProfile", profile);
//        modelAndView.addObject("UserProfile", profile);
//       // modelAndView.addObject("ProfilePicture", "https://asico-upload-storage.darkube.app/asico/avatar/avatar.jpeg");
//        httpSession.setAttribute("ProfilePicture", imageUrl);
        return modelAndView;
    }

    @RequestMapping(value = "/read-report")
    public ModelAndView read_report(HttpSession httpSession) {

        ModelAndView modelAndView = new ModelAndView(baseDirectory + "reading-report");

        return modelAndView;
    }

    @RequestMapping(value = "/panel-error")
    public ModelAndView paanel_error(HttpSession httpSession, @RequestParam(required = false) String
            errorMessage, Model model) {

        ModelAndView modelAndView = new ModelAndView(baseDirectory + "panel-error");
        String codeMeli = (String) httpSession.getAttribute("codeMeli");
        UserProfileEntity userProfileEntity = userProfileService.searchByNationalCode(codeMeli);
        if (userProfileEntity == null) {
            UserProfileEntity userProfile = new UserProfileEntity();
            userProfile.setCodemeli(codeMeli);
            userProfile.setFullname("کاربر سامانه ");
            userProfile.setPicture(imageUrl);
            httpSession.setAttribute("UserProfile", userProfile);
            modelAndView.addObject("UserProfile", userProfile);
        } else {
            httpSession.setAttribute("UserProfile", userProfileEntity);
            modelAndView.addObject("UserProfile", userProfileEntity);
        }
        model.addAttribute("errorMessage", errorMessage);


        return modelAndView;
    }

    @RequestMapping(value = "/panel-register")
    public ModelAndView panel_register(HttpSession httpSession, @RequestParam(required = false) String
            type, Model model) {

        ModelAndView modelAndView = new ModelAndView(baseDirectory + "panel-register");
        String codeMeli = (String) httpSession.getAttribute("codeMeli");
        UserProfileEntity userProfileEntity = userProfileService.searchByNationalCode(codeMeli);
        if (userProfileEntity == null) {
            UserProfileEntity userProfile = new UserProfileEntity();
            userProfile.setCodemeli(codeMeli);
            userProfile.setFullname("کاربر سامانه");
            userProfile.setPicture(imageUrl);
            httpSession.setAttribute("UserProfile", userProfile);
            modelAndView.addObject("UserProfile", userProfile);
        } else {
            httpSession.setAttribute("UserProfile", userProfileEntity);
            modelAndView.addObject("UserProfile", userProfileEntity);
        }

        System.out.println(type);
        if (type.equals("register")){
            String headerMessage="ثبت درخواست تصدی سمت ";
            String message="درخواست تصدی سمت شما با موفقیت ثبت شد.کد یکتای سامانه به شماره موبای شما ارسال گردید. ";
            model.addAttribute("message", message);
            model.addAttribute("headerMessage", headerMessage);
        }else if (type.equals("profileEdit")){

            String headerMessage = "ثبت ویرایش حساب کاربری";
            String message = "ویرایش پروفایل با موفقیت انجام شد . جهت ادامه فرایند  وارد بخش تصدی سمت شوید. ";
            model.addAttribute("message", message);
            model.addAttribute("headerMessage", headerMessage);
        }else {
            String headerMessage = "";
            String message = " ";
            model.addAttribute("message", message);
            model.addAttribute("headerMessage", headerMessage);
        }
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

    private int UserRequestCunt(String phoneNumber) {

        ArrayList<positionRequest> positionRequests = new ArrayList<>();
        List<positionRequest> positions = positionService.getAll();

        for (positionRequest request : positions) {
            if (!request.isProcess()) {
                positionRequests.add(request);
            }

        }

        return positionRequests.size();
    }


}
