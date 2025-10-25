package com.asico.hr.spotplayer.service;

import com.asico.hr.spotplayer.domain.LicenseModel;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */

public interface License {


    LicenseModel generateLicense(String courseId , String phoneNumber);


}
