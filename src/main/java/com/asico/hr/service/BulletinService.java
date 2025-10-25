package com.asico.hr.service;

import com.asico.hr.domain.BulletinEntity;
import com.asico.hr.domain.BulletinModel;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
public interface BulletinService {


    BulletinModel search(String phoneNumber);

    BulletinModel add(BulletinModel bulletinModel);

    List<BulletinModel> getAll();

    BulletinModel delete(BulletinModel model);
    CompletableFuture<BulletinEntity> saveAsync(BulletinModel model);

}
