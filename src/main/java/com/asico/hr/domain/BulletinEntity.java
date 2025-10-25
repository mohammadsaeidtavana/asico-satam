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
@Table(name = "BULLETIN")
public class BulletinEntity extends BaseEntity<Long> {

    @Column(name = "BULLETIN_PHONE_NUMBER")
    String phoneNumber;

    @Column(name = "BULLETIN_EMAIL")
    String EmailAddress;

    @Column(name = "BULLETIN_PANEL_MODE")
    @Enumerated(EnumType.ORDINAL)
    PanelMode panelMode;

}
