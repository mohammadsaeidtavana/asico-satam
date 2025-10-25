package com.asico.hr.payment.service;

import com.asico.hr.payment.domain.AuthResponse;
import com.asico.hr.payment.domain.ReverseResponse;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */
public interface PaymentService {


    AuthResponse getAuthority(long amount, String phoneNumber, String description, String email);

    ReverseResponse paymentVerify(long amount, String authority);

}
