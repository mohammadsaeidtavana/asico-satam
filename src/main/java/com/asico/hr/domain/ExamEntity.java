package com.asico.hr.domain;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EXAM")
public class ExamEntity extends BaseEntity<Long> {


    @Column(name = "EXAM_COURSE_ID")
    private String courseId;

    @Column(name = "EXAM_PHONENUMBER")
    private String phoneNumber;

    @Column(name = "EXAM_SCORE")
    private String score;

    @Column(name = "EXAM_RESULT")
    private String result;








}
