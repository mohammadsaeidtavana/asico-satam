package com.asico.hr.payment.controller;


import com.asico.hr.domain.CartModel;
import com.asico.hr.domain.CourseModel;
import com.asico.hr.domain.UserCourseModel;
import com.asico.hr.domain.UserModel;
import com.asico.hr.payment.Config.MemoryUtil;
import com.asico.hr.payment.domain.AuthResponse;
import com.asico.hr.payment.domain.OrderModel;
import com.asico.hr.payment.domain.ReverseResponse;
import com.asico.hr.payment.service.OrderService;
import com.asico.hr.payment.service.PaymentService;
import com.asico.hr.service.UserCourseService;
import com.asico.hr.service.UserService;
import com.asico.hr.sms.service.ISmsService;
import com.asico.hr.service.CartService;
import com.asico.hr.service.CourseService;
import com.asico.hr.spotplayer.domain.LicenseModel;
import com.asico.hr.spotplayer.service.License;
import jakarta.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */
@Controller
@RequestMapping("/v1/payment")
public class PaymentController {

    UserCourseService userCourseService;

    CartService cartService;

    CourseService courseService;

    PaymentService paymentService;

    License license;

    OrderService orderService;

    ISmsService smsService;

    UserService userService;


    private static org.slf4j.Logger logger = LoggerFactory.getLogger(PaymentController.class);


    public PaymentController(UserCourseService userCourseService, CartService cartService, CourseService courseService,
                             PaymentService paymentService, License license, OrderService orderService, ISmsService smsService,
                             UserService userService) {
        this.userCourseService = userCourseService;
        this.cartService = cartService;
        this.courseService = courseService;
        this.paymentService = paymentService;
        this.license = license;
        this.orderService = orderService;
        this.smsService = smsService;
        this.userService = userService;
    }

    @GetMapping(value = "/request")
    public RedirectView getAuth(HttpSession httpSession) {

        String redirectUrl = null;
        long totalPayPrice = 0;
        AtomicReference<String> path = new AtomicReference<>();
        String phoneNumber = String.valueOf(httpSession.getAttribute("phoneNumber"));


        List<CartModel> carts = cartService.search(phoneNumber);
        ArrayList<CourseModel> UsercourseList = new ArrayList<>();

        carts.forEach(course -> {
            CourseModel courseDetails = courseService.findByCourseId(course.getCourseId());
            UsercourseList.add(courseDetails);
        });

        if (httpSession.getAttribute("discount") == null || httpSession.getAttribute("discount").equals("false")) {
            totalPayPrice = cartService.calculateTotalAfterDiscount(UsercourseList) * 10;
        } else {
            long rate = (long) httpSession.getAttribute("discountRate");
            long total = cartService.calculateTotalAfterDiscount(UsercourseList) * 10;
            totalPayPrice = total - (total * rate / 100);
        }


        if (totalPayPrice == 0) {

            List<CartModel> userCourses = cartService.search(phoneNumber);
            userCourses.forEach(course -> {

                // insert into user courses
                UserCourseModel courseModel = new UserCourseModel();

                courseModel.setPhoneNumber(phoneNumber);
                courseModel.setCourseId(course.getCourseId());
                userCourseService.saveAsync(courseModel);

                // insert into order table
                // get course lisence
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

                CourseModel model = courseService.findByCourseId(course.getCourseId());

                OrderModel order = new OrderModel();
                order.setCourseId(course.getCourseId());
                order.setOrderDetails(model.getName());
                order.setPhoneNumber(phoneNumber);
                order.setCoursePrice(String.valueOf(model.getDiscountPrice()));
                order.setStatus(0);
                order.setRefId("دوره رایگان");
                order.setDate(formatter.format(date));
                order.setLisence("دوره رایگان");
                order.setPan("");
                orderService.save(order);

                // remove from user card
                cartService.delete(phoneNumber, course.getCourseId());

                CourseModel redirectModel = courseService.findByCourseId(courseModel.getCourseId());
                path.set(redirectModel.getCoursePage());

            });


            // send welcome sms to user
            UserModel userModel = userService.search(phoneNumber);
            smsService.sendWelcomeCourseSmsAsync(phoneNumber, userModel.getName());

            httpSession.removeAttribute("discountRate");
            httpSession.removeAttribute("discount");
            redirectUrl = "https://javabox.ir" + path.get();
        } else {


            String description = "";
            for (CourseModel courseModel : UsercourseList) {

                description += courseModel.getName() + " || ";
            }
            AuthResponse response = paymentService.getAuthority(totalPayPrice, phoneNumber, description,
                    "info@javabox.ir");


            if (response.getData().getCode() == 100) {
                MemoryUtil.clear(response.getData().getAuthority());
                MemoryUtil.putValue(response.getData().getAuthority(), phoneNumber);

                redirectUrl = "https://www.zarinpal.com/pg/StartPay/" + response.getData().getAuthority();
            } else {

                redirectUrl = "https://javabox.ir/userpanle";
            }
        }
        return new RedirectView(redirectUrl);
    }

    @GetMapping(value = "/verify")
    public ModelAndView verify(@RequestParam("Authority") String Authority, @RequestParam("Status") String Status,
                               HttpSession httpSession) {
        ModelAndView view = null;
        try {

            if (Status.equals("NOK")) {

                view = new ModelAndView("redirect:/payment-error");

            } else if (Status.equals("OK")) {
                long amount = getTotalPrice(MemoryUtil.getValue(Authority));

                ReverseResponse reverseResponse = paymentService.paymentVerify(amount, Authority);
                if (reverseResponse.getData().getCode() == 100 || reverseResponse.getData().getCode() == 101) {

                    // transaction
                    // String phoneNumber = String.valueOf(httpSession.getAttribute("phoneNumber"));
                    String phoneNumber = MemoryUtil.getValue(Authority); // get phone number
                    List<CartModel> userCourses = cartService.search(phoneNumber);
                    userCourses.forEach(course -> {

                        // insert into user courses
                        UserCourseModel courseModel = new UserCourseModel();
                        courseModel.setPhoneNumber(phoneNumber);
                        courseModel.setCourseId(course.getCourseId());
                        userCourseService.saveAsync(courseModel);

                        // insert into order table
                        // get course lisence
                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

                        CourseModel model = courseService.findByCourseId(course.getCourseId());

                        OrderModel order = new OrderModel();
                        order.setCourseId(course.getCourseId());
                        order.setOrderDetails(model.getName());
                        order.setPhoneNumber(phoneNumber);
                        order.setCoursePrice(String.valueOf(model.getDiscountPrice()));
                        order.setStatus(0);
                        order.setRefId(reverseResponse.getData().getRef_id());
                        order.setDate(formatter.format(date));
                        order.setLisence(getLicenseKey(course.getCourseId(), phoneNumber));
                        order.setPan(reverseResponse.getData().getCard_pan());
                        orderService.save(order);

                        // remove from user card
                        cartService.delete(phoneNumber, course.getCourseId());

                    });


                    // send welcome sms to user

                    UserModel userModel = userService.search(phoneNumber);
                    smsService.sendWelcomeCourseSmsAsync(phoneNumber, userModel.getName());

                    // redirect to profile financial
                    view = new ModelAndView("redirect:/profile-financial");


                } else {

                    view = new ModelAndView("redirect:/payment-error");
                    /// view erro page
                }
            }


        } catch (Exception e) {


            logger.info("======= exception =======");
            logger.info(e.getMessage());

            System.out.println("======= exception =======");
            e.printStackTrace();
            view = new ModelAndView("redirect:/payment-error");

        }
        MemoryUtil.clear(Authority);
        return view;
    }


    private String getLicenseKey(String courseID, String phoneNumber) {

        LicenseModel model = license.generateLicense(courseID, phoneNumber);
        return model.getKey();
    }

    private long getTotalPrice(String phoneNumber) {
        List<CartModel> carts = cartService.search(phoneNumber);

        ArrayList<CourseModel> UsercourseList = new ArrayList<>();

        carts.forEach(course -> {
            CourseModel courseDetails = courseService.findByCourseId(course.getCourseId());
            UsercourseList.add(courseDetails);
        });

        long totalPayPrice = cartService.calculateTotalAfterDiscount(UsercourseList) * 10;


        return totalPayPrice;
    }

}
