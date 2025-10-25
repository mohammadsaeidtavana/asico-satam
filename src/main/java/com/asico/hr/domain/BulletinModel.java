package com.asico.hr.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BulletinModel {

    String phoneNumber;
    String EmailAddress;
    PanelMode panelMode;


}
