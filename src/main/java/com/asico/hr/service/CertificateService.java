package com.asico.hr.service;

import com.asico.hr.domain.CertificateModel;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
public interface CertificateService {

    List<CertificateModel> search(String phoneNumber);

    CertificateModel save(CertificateModel certificateModel);

    List<CertificateModel> getAll();

    CertificateModel delete(String phoneNumber,String courseId);

    CertificateModel searchPhoneNumberAndCourse(String phoneNumber , String CourseId);

    int countNumberOfCertificate(String phoneNumber);

    int countNumberOfCertificateNotIssue(String phoneNumber);

    int issueCertificate(String phoneNumber , String CourseId);
    CompletableFuture<CertificateModel> saveAsync(CertificateModel certificateModel);


}
