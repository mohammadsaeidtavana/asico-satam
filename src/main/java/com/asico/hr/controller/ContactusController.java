package com.asico.hr.controller;

import com.asico.hr.domain.ContactusModel;
import com.asico.hr.domain.PanelMode;
import com.asico.hr.service.ContactusService;
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
@RequestMapping("/v1/contact/save")
public class ContactusController {

    private ContactusService contactusService;

    public ContactusController(ContactusService contactusService) {
        this.contactusService = contactusService;
    }

    @PostMapping(value = "/home")
    public ModelAndView redirectHome(@RequestParam("email") String email , @RequestParam("name") String name,
                                     @RequestParam("text") String text ,HttpServletRequest request , HttpSession httpSession) {



        String phoneNumber= String.valueOf(httpSession.getAttribute("phoneNumber"));

        ContactusModel model = new ContactusModel();
        model.setEmailAddress(email);
        model.setPhoneNumber(phoneNumber);
        model.setPanelMode(PanelMode.PAGE);
        model.setName(name);
        model.setText(text);
        contactusService.saveAsync(model);
        ModelAndView view = new ModelAndView("redirect:/home");
        return view;

    }


    @PostMapping(value = "/userpanel")
    public ModelAndView redirectUserPanel(@RequestParam("email") String email , @RequestParam("name") String name,
                                          @RequestParam("text") String text ,HttpServletRequest request , HttpSession httpSession) {

        System.out.println(name);
        System.out.println(text);
        String phoneNumber= String.valueOf(httpSession.getAttribute("phoneNumber"));
        ContactusModel model = new ContactusModel();
        model.setEmailAddress(email);
        model.setPhoneNumber(phoneNumber);
        model.setPanelMode(PanelMode.PAGE);
        model.setName(name);
        model.setText(text);
        contactusService.saveAsync(model);
        ModelAndView view = new ModelAndView("redirect:/userpanle");
        return view;

    }
}