package com.asico.hr.payment.domain;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {



    String phoneNumber;


    private String courseId;


    private String refId;

    private String orderDetails;


    private String lisence;

    private String coursePrice;


    private int status;  //  0 success  1 fail


    private String date;

    private String pan;



}
