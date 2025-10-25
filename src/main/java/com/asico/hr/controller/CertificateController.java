package com.asico.hr.controller;


import com.asico.hr.domain.CertificateModel;
import com.asico.hr.service.CertificateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */
@Controller
@RequestMapping("/v1/certificate")
public class CertificateController {


    CertificateService service;

    public CertificateController(CertificateService service) {
        this.service = service;
    }

    @GetMapping(value = "/request")
    public ModelAndView redirectHome(@RequestParam("courseid") String courseId,
                                     HttpServletRequest request, HttpSession httpSession ,Model model ) {

        ModelAndView view = null;
        String phoneNumber = String.valueOf(httpSession.getAttribute("phoneNumber"));

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

        try {

            CertificateModel certificateModel = service.searchPhoneNumberAndCourse(phoneNumber, courseId);

            List<CertificateModel> list=service.search(phoneNumber);
            model.addAttribute("certificateList", list);

            if (certificateModel == null) {
                CertificateModel certificatmodel = new CertificateModel();
                certificatmodel.setCourseId(courseId);
                certificatmodel.setPhoneNumber(phoneNumber);
                certificatmodel.setIssue(false);
                certificatmodel.setIssueDate(null);
                certificatmodel.setRequestDate(formatter.format(date));
                certificatmodel.setCertificateStatus(0);
                service.saveAsync(certificatmodel);

                // send sms to user
                view = new ModelAndView("redirect:/profile-courses");
            } else {


                // show message that " shoma ghablan sabte nam kardeind"
                view = new ModelAndView("redirect:/profile-courses");
            }

        } catch (Exception e) {
            CertificateModel certificatmodel = new CertificateModel();
            certificatmodel.setCourseId(courseId);
            certificatmodel.setPhoneNumber(phoneNumber);
            certificatmodel.setIssue(false);
            certificatmodel.setIssueDate(null);
            certificatmodel.setRequestDate(formatter.format(date));
            certificatmodel.setCertificateStatus(0);
            service.saveAsync(certificatmodel);
            // send sms to user
            view = new ModelAndView("redirect:/profile-courses");
        }


        return view;

    }


    @GetMapping(value = "/issue")
    public ModelAndView issure(@RequestParam("courseid") String courseId, @RequestParam("phone") String phone,
                               HttpServletRequest request, HttpSession httpSession) {

        ModelAndView view = null;

        try {

            int code = service.issueCertificate(phone, courseId);
            view = new ModelAndView("redirect:/profile-courses");

        } catch (Exception e) {

            view = new ModelAndView("redirect:/profile-courses");
        }

        return view;

    }

}
