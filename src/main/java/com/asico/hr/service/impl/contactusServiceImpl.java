package com.asico.hr.service.impl;


import com.asico.hr.domain.ContactusEntity;
import com.asico.hr.domain.ContactusModel;
import com.asico.hr.domain.config.MapperUtil;
import com.asico.hr.service.ContactusService;
import com.asico.hr.repository.ContactusRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
@Service
public class contactusServiceImpl implements ContactusService {

    ContactusRepository contactusRepository;

    public contactusServiceImpl(ContactusRepository contactusRepository) {
        this.contactusRepository = contactusRepository;
    }

    @Override
    public ContactusModel search(String phoneNumber) {
        return null;
    }

    @Override
    public ContactusModel add(ContactusModel contactusModel) {
        return null;
    }

    @Override
    public List<ContactusModel> getAll() {
        return null;
    }

    @Override
    public ContactusModel delete(ContactusModel model) {
        return null;
    }


    @Async
    @Override
    public CompletableFuture<ContactusModel> saveAsync(ContactusModel model) {

        contactusRepository.save(MapperUtil.map(model, ContactusEntity.class));
        return CompletableFuture.completedFuture(model);
    }
}
