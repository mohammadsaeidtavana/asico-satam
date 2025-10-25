package com.asico.hr.service.impl;

import com.asico.hr.domain.studyEntity;
import com.asico.hr.service.StudyService;
import com.asico.hr.repository.StudyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */

@Service
public class StudyServiceImpl implements StudyService {


    StudyRepository studyRepository;

    public StudyServiceImpl(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    @Override
    public List<studyEntity> search(String phoneNumber) {
        return null;
    }

    @Override
    public studyEntity save(studyEntity studyEntity) {
        studyRepository.save(studyEntity);
        return studyEntity;
    }

    @Override
    public List<studyEntity> getAll() {
        return studyRepository.findAll();
    }

    @Override
    public studyEntity delete(String phoneNumber) {
        return null;
    }
}
