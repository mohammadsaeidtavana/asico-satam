package com.asico.hr.entity.person;

import com.asico.hr.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Skill")
public class Skill extends BaseEntity<Long> {

    @Column(name = "technical", length = 200)
    private String technical; // مهارت تخصصی
    @Column(name = "soft", length = 200)
    private String soft;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfileEntity userProfile;
}
