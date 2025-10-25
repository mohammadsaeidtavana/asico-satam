package com.asico.hr.spotplayer.Repository;

import com.asico.hr.spotplayer.domain.LicenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */
public interface LicenseRepository extends JpaRepository<LicenseEntity,Long> {

    List<LicenseEntity> findByPhoneNumber(String phoneNumber);

}
