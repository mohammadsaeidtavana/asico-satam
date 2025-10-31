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
@Table(name = "Research")
public class Research extends BaseEntity<Long> {

    @Column(name = "title", length = 200)
    private String title;
    @Column(name = "objective", length = 500)
    private String objective;
    @Column(name = "link", length = 500)
    private String link;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfileEntity userProfile;
}
