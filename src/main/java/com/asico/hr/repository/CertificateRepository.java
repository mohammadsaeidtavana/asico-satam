package com.asico.hr.repository;

import com.asico.hr.domain.CertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
@Repository
@Transactional
public interface CertificateRepository extends JpaRepository<CertificateEntity, Long> {


    List<CertificateEntity> findByPhoneNumber(String phoneNumber);

    CertificateEntity findByPhoneNumberAndAndCourseId(String phoneNumber , String courseId);

    List<CertificateEntity> findByPhoneNumberAndIssue(String phoneNumber , boolean issue);


    @Modifying
    @Query("update CertificateEntity c set c.issue=:issue ,c.issueDate=:date ,c.certificateStatus=1 where c.phoneNumber=:phone and c.courseId=:course")
    int issueCertificate(@Param("phone") String phoneNumber , @Param("course") String courseId ,@Param("date") String issueDate,
                         @Param("issue") boolean issue);

    @Modifying
    @Query("update CertificateEntity u set u.phoneNumber=:newPhone where u.phoneNumber=:oldPhone")
    int updatePhoneNumber(@Param("oldPhone") String oldPhone, @Param("newPhone") String newPhone);





}
