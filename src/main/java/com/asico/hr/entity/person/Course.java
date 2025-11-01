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
@Table(name = "Course")
public class Course extends BaseEntity<Long> {

    @Column(name = "title", nullable = false, length = 200)
    private String title;
    @Column(name = "organizer", length = 200)
    private String organizer;
    @Column(name = "received_date")
    private String receivedDate;
    @Column(name = "duration", length = 100)
    private String duration;
    @Column(name = "file", length = 500)
    private String file; // فایل اختیاری

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfileEntity userProfile;
}
