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
@Table(name = "CART")
public class CartEntity extends BaseEntity<Long> {

    @Column(name = "CART_PHONE_NUMBER" )
    private String phoneNumber;
    @Column(name = "CART_COURSE_ID" )
    private String courseId;

}
