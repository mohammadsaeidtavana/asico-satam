package com.asico.hr.service.impl;

import com.asico.hr.domain.CertificateEntity;
import com.asico.hr.domain.CertificateModel;
import com.asico.hr.domain.config.MapperUtil;
import com.asico.hr.service.CertificateService;
import com.asico.hr.repository.CertificateRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */

@Service
public class CertificateServiceImpl implements CertificateService {

    CertificateRepository repository;

    public CertificateServiceImpl(CertificateRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CertificateModel> search(String phoneNumber) {


        return MapperUtil.mapList(repository.findByPhoneNumber(phoneNumber), CertificateModel.class);
    }


    @Override
    public CertificateModel searchPhoneNumberAndCourse(String phoneNumber, String CourseId) {
        return MapperUtil.map(repository.findByPhoneNumberAndAndCourseId(phoneNumber, CourseId), CertificateModel.class);
    }

    @Override
    public CertificateModel save(CertificateModel certificateModel) {
        return null;
    }

    @Override
    public List<CertificateModel> getAll() {


        return MapperUtil.mapList(repository.findAll(), CertificateModel.class);
    }

    @Override
    public CertificateModel delete(String phoneNumber, String courseId) {
        return null;
    }

    @Override
    public int countNumberOfCertificate(String phoneNumber) {

        List<CertificateModel> list = search(phoneNumber);

        return list.size();
    }

    @Override
    public int countNumberOfCertificateNotIssue(String phoneNumber) {

        List<CertificateEntity> list = repository.findByPhoneNumberAndIssue(phoneNumber, true);
        return list.size();
    }

    @Override
    public int issueCertificate(String phoneNumber, String CourseId) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        return repository.issueCertificate(phoneNumber, CourseId, formatter.format(date), true);
    }

    @Async
    @Override
    public CompletableFuture<CertificateModel> saveAsync(CertificateModel certificateModel) {


        repository.save(MapperUtil.map(certificateModel, CertificateEntity.class));
        return CompletableFuture.completedFuture(certificateModel);
    }
}
