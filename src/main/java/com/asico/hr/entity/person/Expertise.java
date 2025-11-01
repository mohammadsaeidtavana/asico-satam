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
@Table(name = "Expertise")
@ToString(exclude = "userProfile")
public class Expertise extends BaseEntity<Long> {

    @Column(name = "field", length = 150)
    private String field;
    @Column(name = "industry", length = 150)
    private String industry;// حوزه صنعت
    @Column(name = "orientation")
    private Integer orientation; // مدت زمان فعالیت (ماه)

    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_profile_id", nullable = false)
    @JoinColumn(name = "user_profile_id", foreignKey = @ForeignKey(name = "fk_experties_user_profile"))
    private UserProfileEntity userProfile;
}
