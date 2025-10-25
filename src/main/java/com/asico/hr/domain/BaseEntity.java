package com.asico.hr.domain;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

/**
 *
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
@MappedSuperclass

public class BaseEntity<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqid-gen")
    private T id;
}
