package com.asico.hr.service.impl;

import com.asico.hr.domain.person.UserProfile;
import com.asico.hr.entity.person.UserProfileEntity;
import com.asico.hr.repository.UserProfileRepository;
import com.asico.hr.service.UserProfileService;
import com.asico.hr.utils.UserProfileMapper;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */

@Service("UserProfileService")
@AllArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    UserProfileMapper userProfileMapper;
    UserProfileRepository userProfileRepository;

    @Override
    public UserProfileEntity search(String phoneNumber) {
        return null;
    }

    @Override
    public UserProfileEntity searchByNationalCode(String nationalCode) {
        return userProfileRepository.searchUserProfileByCodemeli(nationalCode);
    }

    @Override
    public List<UserProfileEntity> getAll() {
        return null;
    }

    @Override
    public int delete(UserProfile model) {

        return userProfileRepository.deleteAllByCodemeli(model.getCodemeli());

    }

    @Override
    public UserProfileEntity save(UserProfile model) {

        UserProfileEntity userProfile = userProfileMapper.userProfileDtoToEntity(model);
        userProfileRepository.save(userProfile);
        return userProfile;
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public CompletableFuture<UserProfileEntity> saveAsync(UserProfile model) {

        try {
            System.out.println("============ save async");
            UserProfileEntity userProfile = userProfileMapper.userProfileDtoToEntity(model);
            System.out.println(userProfile.toString());
            UserProfileEntity userProfileEntity = userProfileRepository.saveAndFlush(userProfile);
            //System.out.println("✅ Saved entity ID: " + userProfileEntity.getId());
            return CompletableFuture.completedFuture(userProfileEntity);
        } catch (Exception e) {
            e.printStackTrace(); // 👈 خطای اصلی اینجا ظاهر میشه
            throw e;
        }
    }

    @Override
    public UserProfileEntity update(UserProfile model) {
        return null;
    }
}
