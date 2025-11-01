package com.asico.hr.entity.person;

import com.asico.hr.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Skill")
@ToString(exclude = "userProfile")
public class Skill extends BaseEntity<Long> {

    @Column(name = "technical", length = 200)
    private String technical; // مهارت تخصصی
    @Column(name = "soft", length = 200)
    private String soft;

    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "user_profile_id", nullable = false)
    @JoinColumn(name = "user_profile_id", foreignKey = @ForeignKey(name = "fk_skill_user_profile"))
    private UserProfileEntity userProfile;
}
