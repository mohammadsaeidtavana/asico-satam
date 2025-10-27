package com.asico.hr.domain;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "position_Request")
public class positionRequest extends BaseEntity<Long> {


    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "nationalCode")
    private String nationalCode;

    @Column(name = "company")
    private String company;

    @Column(name = "position")
    private String position;

    @Column(name = "request_Date")
    private Date date;

    @Column(name = "isProcesed")
    private boolean isProcess;

    @Column(name = "isUserAccept")
    private String isUserAccept;

    @Column(name = "refrence_code")
    private int refcode;





}
