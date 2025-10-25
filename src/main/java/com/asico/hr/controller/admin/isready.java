package com.asico.hr.controller.admin;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */
@RestController

public class isready {

    @GetMapping(value = "/is-ready")
    public ResponseEntity addSystemcCourse() {



        return new ResponseEntity(HttpStatus.OK);

    }

}
