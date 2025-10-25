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
@Table(name = "LOG")
public class UserLogEntity extends BaseEntity<Long> {

    @Column(name = "LOG_PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "LOG_DATE")
    private String date;


}
