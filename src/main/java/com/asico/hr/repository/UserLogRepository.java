package com.asico.hr.repository;

import com.asico.hr.domain.UserLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
@Repository
@Transactional
public interface UserLogRepository extends JpaRepository<UserLogEntity, Long> {
    List<UserLogEntity> findFirst3ByOrderByPhoneNumberDesc();

    List<UserLogEntity> findAllByPhoneNumber(String phoneNumber);

    @Modifying
    @Query("update UserLogEntity u set u.phoneNumber=:newPhone where u.phoneNumber=:oldPhone")
    int updatePhoneNumber(@Param("oldPhone") String oldPhone, @Param("newPhone") String newPhone);


}
