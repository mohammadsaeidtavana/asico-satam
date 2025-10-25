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


/**
 * @author  mohammad saeid tavana
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DISCOUNT")
public class DiscountEntity extends BaseEntity<Long> {

    @Column(name = "CODE" )
    private String code;
    @Column(name = "PERSENT" )
    private long persent;
    @Column(name = "status" )
    private boolean status;


}