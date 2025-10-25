package com.asico.hr.domain.config;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleGlobalException(Exception ex, Model model) {

        ex.printStackTrace();
        model.addAttribute("errorMessage", " page not found");
        return "error";
    }



}
