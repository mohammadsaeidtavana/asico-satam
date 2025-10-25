package com.asico.hr.controller;

import com.asico.hr.domain.BulletinModel;
import com.asico.hr.domain.PanelMode;
import com.asico.hr.service.BulletinService;
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
@RequestMapping("/v1/bulletin/save")
public class BulletinController {

    private BulletinService bulletinService;

    public BulletinController(BulletinService bulletinService) {
        this.bulletinService = bulletinService;
    }

    @PostMapping(value = "/home")
    public ModelAndView redirectHome(@RequestParam("email") String email , HttpServletRequest request , HttpSession httpSession) {



        String phoneNumber= String.valueOf(httpSession.getAttribute("phoneNumber"));
        System.out.println(phoneNumber);
        BulletinModel model = new BulletinModel();
        model.setEmailAddress(email);
        model.setPhoneNumber(phoneNumber);
        model.setPanelMode(PanelMode.PAGE);
        bulletinService.saveAsync(model);
        System.out.println(model.toString());
        ModelAndView view = new ModelAndView("redirect:/home");
        return view;

    }


    @PostMapping(value = "/userpanel")
    public ModelAndView redirectUserPanel(@RequestParam("email") String email, HttpServletRequest request, HttpSession httpSession) {

        String phoneNumber= String.valueOf(httpSession.getAttribute("phoneNumber"));
        System.out.println(phoneNumber);
        BulletinModel model = new BulletinModel();
        model.setEmailAddress(email);
        model.setPhoneNumber(phoneNumber);
        model.setPanelMode(PanelMode.PAGE);
        bulletinService.saveAsync(model);
        System.out.println(model.toString());
        ModelAndView view = new ModelAndView("redirect:/userpanle");
        return view;

    }
}