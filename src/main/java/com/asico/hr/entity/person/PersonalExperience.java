package com.asico.hr.entity.person;

import com.asico.hr.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PersonalExperience")
@ToString(exclude = "userProfile")
public class PersonalExperience  extends BaseEntity<Long> {

    @Column(name = "company_name", length = 200)
    private String companyName;          // نام شرکت
    @Column(name = "start_date")
    private String startDate;         // تاریخ شروع تصدی
    @Column(name = "end_date")
    private String endDate;           //
    @Column(name = "official_gazette_link", length = 500)
    private String officialGazetteLink; // فایل روزنامه رسمی

    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_profile_id", nullable = false)
    @JoinColumn(name = "user_profile_id", foreignKey = @ForeignKey(name = "fk_personalexperience_user_profile"))
    private UserProfileEntity userProfile;
}
