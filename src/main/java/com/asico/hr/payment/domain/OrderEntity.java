package com.asico.hr.payment.domain;

import com.asico.hr.domain.BaseEntity;
import jakarta.persistence.*;
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
@Entity
@Table(name = "USER_ORDER")
public class OrderEntity extends BaseEntity<Long> {

    @Column(name = "USER_ORDER_PHONE_NUMBER")
    String phoneNumber;

    @Column(name = "USER_ORDER_COURSE_ID" )
    private String courseId;

    @Column(name = "USER_ORDER_REF_ID" )
    private String refId;

    @Column(name = "USER_ORDER_DETAILS" )
    private String orderDetails;

    @Column(name = "USER_ORDER_COURSE_LISENCE" )
    private String lisence;

    @Column(name = "USER_ORDER_COURSE_PRICE" )
    private String coursePrice;

    @Column(name = "USER_ORDER_STATUS" )
    private int status;  //  0 success  1 fail

    @Column(name = "USER_ORDER_DATE" )
    private String date;

    @Column(name = "USER_ORDER_PAN" )
    private String pan;




}
