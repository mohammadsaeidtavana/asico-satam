package com.asico.hr.repository;

import com.asico.hr.domain.BulletinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
@Repository
@Transactional
public interface BulletinRepository extends JpaRepository<BulletinEntity,Long> {

    @Modifying
    @Query("update BulletinEntity u set u.phoneNumber=:newPhone where u.phoneNumber=:oldPhone")
    int updatePhoneNumber(@Param("oldPhone") String oldPhone, @Param("newPhone") String newPhone);


}
