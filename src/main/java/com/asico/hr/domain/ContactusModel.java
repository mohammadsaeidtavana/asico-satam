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
public class ContactusModel extends BaseEntity<Long> {

    String phoneNumber;


    String EmailAddress;


    String name;

    String text;

    PanelMode panelMode;

}
