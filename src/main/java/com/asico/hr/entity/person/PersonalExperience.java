package com.asico.hr.entity.person;

import com.asico.hr.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PersonalExperience")
public class PersonalExperience  extends BaseEntity<Long> {

    @Column(name = "company_name", length = 200)
    private String companyName;          // نام شرکت
    @Column(name = "start_date")
    private LocalDate startDate;         // تاریخ شروع تصدی
    @Column(name = "end_date")
    private LocalDate endDate;           //
    @Column(name = "official_gazette_link", length = 500)
    private String officialGazetteLink; // فایل روزنامه رسمی

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfileEntity userProfile;
}
