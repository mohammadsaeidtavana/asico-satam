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
@Entity
@Table(name = "CERTIFICATE")
public class CertificateEntity extends BaseEntity<Long> {


    @Column(name = "CERTIFICATE_COURSE_ID")
    private String courseId;

    @Column(name = "CERTIFICATE_PHONENUMBER")
    private String phoneNumber;

    @Column(name = "CERTIFICATE_IS_ISSUE")
    private boolean issue;

    @Column(name = "CERTIFICATE_ISSUE_DATE")
    private String issueDate;

    @Column(name = "CERTIFICATE_REQUEST_DATE")
    private String requestDate;

    @Column(name = "CERTIFICATE_LINK")
    private String certificateLink;

    @Column(name = "CERTIFICATE_ISSUE_STATUS")
    private int certificateStatus;


}
