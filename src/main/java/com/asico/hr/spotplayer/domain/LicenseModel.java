package com.asico.hr.spotplayer.domain;

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
public class LicenseModel {


    private String phoneNumber;


    private String spotPlayerCourseId;

    private String courseId;

    private String _id;


    private String key;


    private String url;
}
