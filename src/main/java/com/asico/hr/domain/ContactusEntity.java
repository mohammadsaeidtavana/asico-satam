package com.asico.hr.domain;

import jakarta.persistence.*;
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
@Entity
@Table(name = "CONTACT")
public class ContactusEntity extends BaseEntity<Long> {

    @Column(name = "CONTACT_PHONE_NUMBER")
    String phoneNumber;

    @Column(name = "CONTACT_EMAIL")
    String EmailAddress;

    @Column(name = "CONTACT_NAME")
    String name;

    @Column(name = "CONTACT_MESSAGE")
    String text;

    @Column(name = "CONTACT_PANEL_MODE")
    @Enumerated(EnumType.ORDINAL)
    PanelMode panelMode;

}
