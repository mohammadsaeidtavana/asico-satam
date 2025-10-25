package com.asico.hr.service;

import com.asico.hr.domain.ContactusModel;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
public interface ContactusService {




    ContactusModel search(String phoneNumber);

    ContactusModel add(ContactusModel contactusModel);

    List<ContactusModel> getAll();

    ContactusModel delete(ContactusModel model);

    CompletableFuture<ContactusModel> saveAsync(ContactusModel model);

}
