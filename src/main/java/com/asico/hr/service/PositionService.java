package com.asico.hr.service;

import com.asico.hr.domain.positionRequest;

import java.util.List;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
public interface PositionService {

    List<positionRequest> search(String phoneNumber);

    positionRequest save(positionRequest studyEntity);

    List<positionRequest> getAll();

    positionRequest delete(String phoneNumber);


}
