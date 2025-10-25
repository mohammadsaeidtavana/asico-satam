package com.asico.hr.service;

import com.asico.hr.domain.studyEntity;

import java.util.List;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
public interface StudyService {

    List<studyEntity> search(String phoneNumber);

    studyEntity save(studyEntity studyEntity);

    List<studyEntity> getAll();

    studyEntity delete(String phoneNumber);


}
