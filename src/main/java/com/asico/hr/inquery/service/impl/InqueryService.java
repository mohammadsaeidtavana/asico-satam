package com.asico.hr.inquery.service.impl;


import com.asico.hr.inquery.domain.nationalcode.NationalCodeRequestApi;
import com.asico.hr.inquery.domain.nationalcode.NationalCodeResponseApi;
import com.asico.hr.inquery.domain.shahkar.ShahkarRequestApi;
import com.asico.hr.inquery.domain.shahkar.ShahkarResponseApi;
import com.google.gson.Gson;
import com.squareup.okhttp.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author m.tavana
 * @since 2025\
 */
@Service("InqueryService")
public class InqueryService implements com.asico.hr.inquery.service.InqueryService {


    @Override
    public NationalCodeResponseApi nationalCodeCheck(NationalCodeRequestApi requestApi) {
        NationalCodeResponseApi apiResponse = null;
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"nationalCode\": \""+requestApi.getNationalCode()+"\"\r\n}");
        Request request = new Request.Builder()
                .url("https://bhub.satpay.ir/service/nationalCode/validation")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2OGY4NzhlYzUyZjMzYzQ0Njc5YTZmNWQiLCJ1dWlkIjoiODRmOGIwNjgtMDg4ZS00NjczLTkyOTQtYTczNWJkNjI3NTMxIiwiaWF0IjoxNzYxMTE0MzQ4fQ.jDy6zQaUBR53gmsx_-PXJnFUsCVwR9BBqw0uC6cE6HQ")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                Gson gson = new Gson();
                apiResponse = gson.fromJson(response.body().string(), NationalCodeResponseApi.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
           return null;
        }
        return apiResponse;
    }

    @Override
    public ShahkarResponseApi shahkar(ShahkarRequestApi requestApi) {

        ShahkarResponseApi apiResponse=null;
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"nationalCode\": \""+requestApi.getNationalCode()+"\",\r\n    \"mobileNumber\": \""+requestApi.getMobileNumber()+"\"\r\n}");
        Request request = new Request.Builder()
                .url("https://bhub.satpay.ir/service/bv/shahkar")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2OGY4NzhlYzUyZjMzYzQ0Njc5YTZmNWQiLCJ1dWlkIjoiODRmOGIwNjgtMDg4ZS00NjczLTkyOTQtYTczNWJkNjI3NTMxIiwiaWF0IjoxNzYxMTE0MzQ4fQ.jDy6zQaUBR53gmsx_-PXJnFUsCVwR9BBqw0uC6cE6HQ")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {

                Gson gson = new Gson();
                apiResponse = gson.fromJson(response.body().string(), ShahkarResponseApi.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return apiResponse;
    }
}
