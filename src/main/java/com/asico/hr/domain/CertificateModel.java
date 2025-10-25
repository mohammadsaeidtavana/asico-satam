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

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CertificateModel {

    private String courseId;

    private String phoneNumber;


    private boolean issue;


    private String issueDate;


    private String requestDate;

    private String certificateLink;

    private int certificateStatus;


}
