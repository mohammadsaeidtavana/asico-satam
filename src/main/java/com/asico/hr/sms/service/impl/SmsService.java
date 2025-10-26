package com.asico.hr.sms.service.impl;


import com.asico.hr.sms.config.ConfigParam;
import com.asico.hr.sms.config.MemoryUtil;
import com.asico.hr.sms.domain.*;
import com.squareup.okhttp.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author m.tavana
 * @since 2025\
 */
@Service("smsService")
public class SmsService implements com.asico.hr.sms.service.SmsService {

    ConfigParam _param;

    public SmsService(ConfigParam _param) {
        this._param = _param;
    }

    @Async
    @Override
    public CompletableFuture<OtpResponse> sendWelcomeCourseSmsAsync(String phoneNumber, String userName) {

        OkHttpClient client = new OkHttpClient();

        String content = "receptor=" + phoneNumber + "&template=" + _param.smsWelcomeTemplate
                + "&type=" + OtpType.TEXT.getType() + "&param1=" + userName;
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(_param.otpWebsiteUrl)
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("apikey", _param.otpApiKey)
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        OtpResponse otpResponse = new OtpResponse();
        Result result = new Result();
        result.setCode(response.code());
        result.setMessage(response.message());
        otpResponse.setResult(result);
        System.out.println(" response " + response);

        return CompletableFuture.completedFuture(otpResponse);
    }

    @Async
    @Override
    public CompletableFuture<OtpResponse> sendCodeAsync(OtpRequest otpRequest) {

        OtpResponse response = null;
        if (MemoryUtil.getValue(otpRequest.getPhoneNumber()) == null) {
            try {
                String otpCode = generateOptCode();
                response = sendOtpCode(otpRequest.getPhoneNumber(), otpCode);

                if (response.getResult().getCode() == 200) {
                    MemoryUtil.putValue(otpRequest.getPhoneNumber(), otpCode);
                } else {
                    //throw new SmsExeption(1, "otp sms cant  send message");

                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new SmsExeption(1, "otp sms cant send message");
            }
        } else {


            Result result = new Result();
            result.setCode(400);
            result.setMessage("code has been sent please wait for 120 second");
            response = new OtpResponse();
            response.setResult(result);
            // code ghablann ersal shode ast .
        }

        return CompletableFuture.completedFuture(response);
    }

    @Override
    public OtpResponse sendCode(OtpRequest otpRequest) {


        OtpResponse response = null;
        if (MemoryUtil.getValue(otpRequest.getPhoneNumber()) == null) {
            try {
                String otpCode = generateOptCode();
                response = sendOtpCode(otpRequest.getPhoneNumber(), otpCode);

                if (response.getResult().getCode() == 200) {
                    MemoryUtil.putValue(otpRequest.getPhoneNumber(), otpCode);
                } else {
                    throw new SmsExeption(1, "otp sms cant  send message");

                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new SmsExeption(1, "otp sms cant send message");
            }
        } else {


            Result result = new Result();
            result.setCode(400);
            result.setMessage("code has been sent please wait for 120 second");
            response = new OtpResponse();
            response.setResult(result);
            // code ghablann ersal shode ast .
        }

        return response;
    }


    @Override
    public OtpResponse verification(OtpRequest request, String otpCode) {

        String code = MemoryUtil.getValue(request.getPhoneNumber());
        if (code == null || code.isEmpty()) {
            throw new SmsExeption(1, "otp sms code must send first ");
        } else if (code.trim().equals(otpCode.trim())) {

            Result result = new Result();
            result.setCode(200);
            result.setMessage("success");
            OtpResponse otpResponse = new OtpResponse();
            otpResponse.setResult(result);
            return otpResponse;

        } else {
            throw new SmsExeption(1, "otp sms code must sent first / invalid sms code ");
        }
    }

    public OtpResponse sendOtpCode(String phoneNumber, String otpCode) throws Exception {

        OkHttpClient client = new OkHttpClient();

        String content = "receptor=" + phoneNumber + "&template=" + _param.optDefaultTemplate
                + "&type=" + OtpType.TEXT.getType() + "&param1=" + otpCode;
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(_param.otpWebsiteUrl)
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("apikey", _param.otpApiKey)
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        OtpResponse otpResponse = new OtpResponse();
        Result result = new Result();
        result.setCode(response.code());
        result.setMessage(response.message());
        otpResponse.setResult(result);
        System.out.println(" response " + response);
        return otpResponse;

    }

    @Override
    public OtpResponse resend(String phoneNumber) {

        OtpResponse otpResendResponse = null;
        String code = MemoryUtil.getValue(phoneNumber);
        if (code == null || code.equals(null)) {
            try {
                String otpCode = generateOptCode();
                otpResendResponse = sendOtpCode(phoneNumber, otpCode);

                if (otpResendResponse.getResult().getCode() == 200) {
                    MemoryUtil.putValue(phoneNumber, otpCode);
                } else {
                    throw new SmsExeption(1, "otp sms cant  send message");

                }
            } catch (Exception e) {
                throw new SmsExeption(1, "otp code resend fail ");
            }

        } else {
            MemoryUtil.clear(phoneNumber);
            try {
                String otpCode = generateOptCode();
                otpResendResponse = sendOtpCode(phoneNumber, otpCode);

                if (otpResendResponse.getResult().getCode() == 200) {
                    MemoryUtil.putValue(phoneNumber, otpCode);
                } else {
                    throw new SmsExeption(1, "otp sms cant  send message");

                }
            } catch (Exception e) {
                throw new SmsExeption(1, "otp code resend fail ");
            }
        }
        return otpResendResponse;
    }

    private String generateOptCode() {

        Random rand = new Random();

        int randomNumber = rand.nextInt(900000) + 100000;

        return String.valueOf(randomNumber);
    }
}
