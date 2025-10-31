package com.asico.hr.service.impl;

import com.asico.hr.domain.person.UserProfile;
import com.asico.hr.entity.person.UserProfileEntity;
import com.asico.hr.repository.UserProfileRepository;
import com.asico.hr.service.UserProfileService;
import com.asico.hr.utils.UserProfileMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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

        UserProfileEntity userProfile= userProfileMapper.userProfileDtoToEntity(model);
        userProfileRepository.save(userProfile);
        return userProfile;
    }

    @Override
    public CompletableFuture<UserProfileEntity> saveAsync(UserProfile model) {
        return null;
    }

    @Override
    public UserProfileEntity update(UserProfile model) {
        return null;
    }
}
