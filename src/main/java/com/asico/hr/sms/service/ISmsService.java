package com.asico.hr.sms.service;

import com.asico.hr.sms.domain.OtpRequest;
import com.asico.hr.sms.domain.OtpResponse;

import java.util.concurrent.CompletableFuture;

/**
 * @author m.tavana
 * @since 2025\
 */
public interface ISmsService {

    OtpResponse sendCode(OtpRequest request);

    OtpResponse verification(OtpRequest request, String otpCode);

    OtpResponse resend(String phoneNumber);

   CompletableFuture<OtpResponse> sendCodeAsync(OtpRequest request);

    CompletableFuture<OtpResponse> sendWelcomeCourseSmsAsync(String phoneNumber,String userName );


}
