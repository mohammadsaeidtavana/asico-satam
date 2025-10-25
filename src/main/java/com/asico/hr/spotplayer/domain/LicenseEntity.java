package com.asico.hr.spotplayer.domain;


import com.asico.hr.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author mohammad saeid tavana
 * @since 2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LICENSE")
public class LicenseEntity extends BaseEntity<Long> {


    @Column(name = "LICENSE_PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "LICENSE_SP_COURSE_ID" )
    private String spotPlayerCourseId;

    @Column(name = "COURSE_ID")
    private String courseId;

    @Column(name = "LICENSE_ID" )
    private String _id;

    @Column(name = "LICENSE_KEY" )
    private String key;

    @Column(name = "LICENSE_URL" )
    private String url;
}
