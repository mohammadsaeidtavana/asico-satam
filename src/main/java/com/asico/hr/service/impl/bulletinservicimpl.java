package com.asico.hr.service.impl;


import com.asico.hr.domain.BulletinEntity;
import com.asico.hr.domain.BulletinModel;
import com.asico.hr.domain.config.MapperUtil;
import com.asico.hr.service.BulletinService;
import com.asico.hr.repository.BulletinRepository;
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
public class bulletinservicimpl  implements BulletinService {



    private BulletinRepository bulletinRepository;

    public bulletinservicimpl(BulletinRepository bulletinRepository) {
        this.bulletinRepository = bulletinRepository;
    }

    @Override
    public BulletinModel search(String phoneNumber) {
        return null;
    }


    @Override
    public BulletinModel add(BulletinModel bulletinModel) {

      bulletinRepository.save(MapperUtil.map(bulletinModel, BulletinEntity.class));
      return bulletinModel;

    }
    @Async
    public CompletableFuture<BulletinEntity> saveAsync(BulletinModel model) {
        BulletinEntity savedEntity = bulletinRepository.save(MapperUtil.map(model,BulletinEntity.class));
        return CompletableFuture.completedFuture(savedEntity);
    }

    @Override
    public List<BulletinModel> getAll() {

        List<BulletinEntity> list=bulletinRepository.findAll();
        return MapperUtil.mapList(list, BulletinModel.class);
    }

    @Override
    public BulletinModel delete(BulletinModel model) {
        return null;
    }
}
