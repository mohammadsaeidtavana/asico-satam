package com.asico.hr.payment.service.impl;

import com.asico.hr.payment.Config.PaymentConfig;
import com.asico.hr.payment.domain.AuthResponse;
import com.asico.hr.payment.domain.ReverseResponse;
import com.asico.hr.payment.service.PaymentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.*;
import org.springframework.stereotype.Service;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public AuthResponse getAuthority(long amount, String phoneNumber, String description, String email) {


        OkHttpClient client = new OkHttpClient();

        String json = "{\"merchant_id\":\"" + PaymentConfig.MERCHAND_ID + "\",\"amount\":" + amount + ",\"callback_url\":\""+PaymentConfig.CALLBACKURL+"\",\"description\":\"" + description + "\",\"metadata\":{\"mobile\":\"" + phoneNumber + "\",\"email\":\"" + email + "\"}}";
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url("https://api.zarinpal.com/pg/v4/payment/request.json")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .build();

        AuthResponse authResponse = null;
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            Gson gson = new GsonBuilder().create();
            authResponse = gson.fromJson(responseBody, AuthResponse.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return authResponse;
    }

    @Override
    public ReverseResponse paymentVerify(long amount, String authority) {
        OkHttpClient client = new OkHttpClient();


        String json = "{\"merchant_id\": \"" + PaymentConfig.MERCHAND_ID + "\", \"amount\": " + amount + ", \"authority\": \"" + authority + "\"}";
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url("https://api.zarinpal.com/pg/v4/payment/verify.json")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .build();

        ReverseResponse reverseResponse = null;
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            Gson gson = new GsonBuilder().create();
            reverseResponse = gson.fromJson(responseBody, ReverseResponse.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return reverseResponse;
    }
}
