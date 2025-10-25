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
@Table(name = "study")
public class studyEntity extends BaseEntity<Long> {


    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "year")
    private String year;

    @Column(name = "month")
    private String month;

    @Column(name = "day")
    private String day;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "subject")
    private String subject;

    @Column(name = "name")
    private String name;



}
