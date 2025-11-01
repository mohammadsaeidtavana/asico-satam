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
@Table(name = "Research")
@ToString(exclude = "userProfile")
public class Research extends BaseEntity<Long> {

    @Column(name = "title", length = 200)
    private String title;
    @Column(name = "objective", length = 500)
    private String objective;
    @Column(name = "link", length = 500)
    private String link;

    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "user_profile_id", nullable = false)
    @JoinColumn(name = "user_profile_id", foreignKey = @ForeignKey(name = "fk_research_user_profile"))
    private UserProfileEntity userProfile;
}
