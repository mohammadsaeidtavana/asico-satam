package com.asico.hr.sms.config;


import com.asico.hr.sms.domain.SmsExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
@ControllerAdvice
public class smsExceptionHandler {


    @ExceptionHandler(SmsExeption.class)
    public ResponseEntity handleException(SmsExeption ex) {

        return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public final ResponseEntity handleResourceNotFound(Exception ex) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(ex.toString());
    }


}
