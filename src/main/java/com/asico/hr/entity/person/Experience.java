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
@Table(name = "Experience")
@ToString(exclude = "userProfile")
public class Experience extends BaseEntity<Long> {
    @Column(name = "company", length = 200)
    private String company;
    @Column(name = "city", length = 150)
    private String city;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "end_date")
    private String endDate;
    @Column(name = "duration", length = 100)
    private String duration;
    @Column(name = "position", length = 150)
    private String position;

    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_profile_id", nullable = false)
    @JoinColumn(name = "user_profile_id", foreignKey = @ForeignKey(name = "fk_experience_user_profile"))
    private UserProfileEntity userProfile;
}
