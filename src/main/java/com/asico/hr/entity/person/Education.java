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
@Table(name = "Education")
public class Education  extends BaseEntity<Long> {

    @Column(name = "degree", length = 100)
    private String degree;
    @Column(name = "field", length = 150)
    private String field;
    @Column(name = "university", length = 200)
    private String university;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "end_date")
    private String endDate;
    @Column(name = "file", length = 500)
    private String file; // برای فایل دانشنامه

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfileEntity userProfile;


}
