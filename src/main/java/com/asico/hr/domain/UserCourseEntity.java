package com.asico.hr.domain;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_COURSE")
public class UserCourseEntity extends BaseEntity<Long> {


    @Column(name = "USER_COURSE_ID")
    private String courseId;
    @Column(name = "USER_COURSE_LICENSE")
    private String courseLicense;
    @Column(name = "USER_COURSE_PHONENUMBER")
    private String phoneNumber;




}
