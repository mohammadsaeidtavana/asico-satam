package com.asico.hr.payment.repository;

import com.asico.hr.payment.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {


    List<OrderEntity> findByPhoneNumber(String phoneNumber);

}
