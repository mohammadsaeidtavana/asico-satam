package com.asico.hr.service.impl;

import com.asico.hr.domain.positionRequest;
import com.asico.hr.service.PositionService;
import com.asico.hr.repository.PositonRequestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */

@Service
public class PositionServiceImpl implements PositionService {


    PositonRequestRepository positonRequestRepository;

    public PositionServiceImpl(PositonRequestRepository positonRequestRepository) {
        this.positonRequestRepository = positonRequestRepository;
    }

    @Override
    public List<positionRequest> search(String phoneNumber) {

        List<positionRequest> responseList = new ArrayList<>();

        List<positionRequest> positionRequests = getAll();
        for (positionRequest positionRequest : positionRequests) {
            if (positionRequest.getPhoneNumber().equals(phoneNumber)) {
                responseList.add(positionRequest);
            }
        }
        return responseList;
    }

    @Override
    public positionRequest save(positionRequest studyEntity) {

        System.out.println("==== save position");
        positonRequestRepository.save(studyEntity);
        return studyEntity;
    }

    @Override
    public List<positionRequest> getAll() {
        return positonRequestRepository.findAll();
    }

    @Override
    public positionRequest delete(String phoneNumber) {
        return null;
    }
}
