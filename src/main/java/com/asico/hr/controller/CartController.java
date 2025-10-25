package com.asico.hr.controller;


import com.asico.hr.domain.CartModel;
import com.asico.hr.domain.DiscountEntity;
import com.asico.hr.repository.DiscountRepository;
import com.asico.hr.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */
@Controller
@RequestMapping("/v1/cart")
public class CartController {


    CartService service;

    DiscountRepository discountRepository;

    public CartController(CartService service, DiscountRepository discountRepository) {
        this.service = service;
        this.discountRepository = discountRepository;
    }

    @GetMapping(value = "/add")
    public ModelAndView addCart(@RequestParam("courseid") String courseId,
                                HttpServletRequest request, HttpSession httpSession, Model model,
                                RedirectAttributes redirectAttributes) {

        ModelAndView view = null;
        String phoneNumber = String.valueOf(httpSession.getAttribute("phoneNumber"));

        CartModel cart = new CartModel();
        cart.setCourseId(courseId);
        cart.setPhoneNumber(phoneNumber);

        service.saveAsync(cart);

        httpSession.setAttribute("userCartsCount", service.search(phoneNumber).size());
        model.addAttribute("userCartsCount", service.search(phoneNumber).size());
        redirectAttributes.addFlashAttribute("userCartsCount", service.search(phoneNumber).size());
        view = new ModelAndView("redirect:/cart");


        return view;

    }

    @GetMapping(value = "/add/discount")
    public ModelAndView addDiscount(@RequestParam("dicountCode") String dicountCode, HttpServletRequest request,
                                    HttpSession httpSession, Model model,
                                    RedirectAttributes redirectAttributes) {
        ModelAndView view = null;
        String phoneNumber = String.valueOf(httpSession.getAttribute("phoneNumber"));
        if (dicountCode == null || dicountCode.equals("")) {

            httpSession.setAttribute("discount", "false");
            view = new ModelAndView("redirect:/cart");
        } else {

            DiscountEntity discount = discountRepository.findByCodeAndStatusIsTrue(dicountCode);
            if (discount == null) {
                httpSession.setAttribute("discount", "false");
                view = new ModelAndView("redirect:/cart");
            } else {

                httpSession.setAttribute("discount", "true");
                httpSession.setAttribute("discountRate", discount.getPersent());
                view = new ModelAndView("redirect:/cart");
            }

        }

        return view;
    }


    @GetMapping(value = "/remove")
    public ModelAndView deleteCart(@RequestParam("courseid") String courseId,
                                   HttpServletRequest request, HttpSession httpSession, Model model,
                                   RedirectAttributes redirectAttributes) {

        String phoneNumber = String.valueOf(httpSession.getAttribute("phoneNumber"));

        CartModel cartModel = service.delete(phoneNumber, courseId);
        ModelAndView cart = new ModelAndView("redirect:/cart");

        return cart;

    }

}
