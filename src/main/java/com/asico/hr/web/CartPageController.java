package com.asico.hr.web;

import com.asico.hr.domain.CartModel;
import com.asico.hr.domain.CourseModel;
import com.asico.hr.service.CartService;
import com.asico.hr.service.CourseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */
@Controller

public class CartPageController {

    CartService cartService;

    CourseService courseService;

    public CartPageController(CartService cartService, CourseService courseService) {
        this.cartService = cartService;
        this.courseService = courseService;
    }

    @RequestMapping(value = "/cart")
    public ModelAndView cart(Model model, HttpSession httpSession) {


//        httpSession.setAttribute("discount", "true");
//        httpSession.setAttribute("discountRate",

        ModelAndView view = null;
        String phoneNumber = String.valueOf(httpSession.getAttribute("phoneNumber"));
        List<CartModel> carts = cartService.search(phoneNumber);


        ArrayList<CourseModel> UsercourseList = new ArrayList<>();

        carts.forEach(course -> {
            CourseModel courseDetails = courseService.findByCourseId(course.getCourseId());
            UsercourseList.add(courseDetails);
        });

        int cartCount = carts.size();
        model.addAttribute("courses", courseService.getAll());
        if (carts == null || carts.isEmpty()) {


            httpSession.setAttribute("userCartsCount", cartCount);
            model.addAttribute("userCartsCount", cartCount);
            view = new ModelAndView("cart-empty").addObject("userCartsCount", cartCount);
            return view;
            //return "cart-empty";
        } else {

            if (httpSession.getAttribute("discount") == null || httpSession.getAttribute("discount").equals("false")) {


                httpSession.setAttribute("totalPrice", cartService.calculateTotalPrice(UsercourseList));
                httpSession.setAttribute("totalPriceAfterDiscount", cartService.calculateTotalAfterDiscount(UsercourseList));
                httpSession.setAttribute("totalDiscount", cartService.calculateTotalDiscount(UsercourseList));

                model.addAttribute("totalPrice", cartService.calculateTotalPrice(UsercourseList));
                model.addAttribute("totalPriceAfterDiscount", cartService.calculateTotalAfterDiscount(UsercourseList));
                model.addAttribute("totalDiscount", cartService.calculateTotalDiscount(UsercourseList));
            } else {

                long rate = (long) httpSession.getAttribute("discountRate");
                long totalPrice = cartService.calculateTotalPrice(UsercourseList);
                long totalpriceAfterDiscount = totalPrice - (totalPrice * rate / 100);

                httpSession.setAttribute("totalPrice", cartService.calculateTotalPrice(UsercourseList));
                httpSession.setAttribute("totalPriceAfterDiscount", totalpriceAfterDiscount);
                httpSession.setAttribute("totalDiscount", cartService.calculateTotalDiscount(UsercourseList)+(totalPrice-totalpriceAfterDiscount));

                model.addAttribute("totalPrice", cartService.calculateTotalPrice(UsercourseList));
                model.addAttribute("totalPriceAfterDiscount", totalpriceAfterDiscount);
                model.addAttribute("totalDiscount", cartService.calculateTotalDiscount(UsercourseList)+(totalPrice-totalpriceAfterDiscount));

            }

            httpSession.setAttribute("userCartsCount", cartCount);
            httpSession.setAttribute("userCarts", UsercourseList);

            model.addAttribute("userCarts", cartCount);
            model.addAttribute("userCarts", UsercourseList);
            view = new ModelAndView("cart").addObject("userCartsCount", cartCount);
            return view;
            // return "cart";
        }

    }
}