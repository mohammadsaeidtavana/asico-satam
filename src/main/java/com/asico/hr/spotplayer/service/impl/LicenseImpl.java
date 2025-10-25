package com.asico.hr.spotplayer.service.impl;

import com.asico.hr.domain.CourseModel;
import com.asico.hr.domain.config.MapperUtil;
import com.asico.hr.service.CourseService;
import com.asico.hr.spotplayer.Repository.LicenseRepository;
import com.asico.hr.spotplayer.domain.LicenseEntity;
import com.asico.hr.spotplayer.domain.LicenseModel;
import com.asico.hr.spotplayer.domain.ResponseModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.asico.hr.spotplayer.service.License;
import com.squareup.okhttp.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */

@Service
public class LicenseImpl implements License {


    private static final String API_KEY = "Y4sgKaO7a1IdMF40i9+GvVGqiAQy9w==";
    LicenseRepository licenseRepository;

    CourseService courseService;


    public LicenseImpl(LicenseRepository licenseRepository, CourseService courseService) {
        this.licenseRepository = licenseRepository;
        this.courseService = courseService;

    }

    @Override
    public LicenseModel generateLicense(String courseId, String phoneNumber) {
        CourseModel course = null;
        try {

            course = courseService.findByCourseId(courseId);
        } catch (Exception e) {

            // code for if the course is not exist
        }
        ResponseModel response = null;
        try {
            response = generate(course.getSpotPlayerCourseId(), phoneNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LicenseModel licenseModel = new LicenseModel();
        licenseModel.setCourseId(courseId);
        licenseModel.setPhoneNumber(phoneNumber);
        licenseModel.setSpotPlayerCourseId(course.getSpotPlayerCourseId());
        licenseModel.setUrl(response.getUrl());
        licenseModel.setKey(response.getKey());
        licenseModel.set_id(response.get_id());

        licenseRepository.save(MapperUtil.map(licenseModel, LicenseEntity.class));
        return licenseModel;
    }

    private ResponseModel generate(String spotPlayerCourseId, String phoneNumber) throws IOException {

        OkHttpClient client = new OkHttpClient();

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("test", true);
            JSONArray courseArray = new JSONArray();
            courseArray.put(spotPlayerCourseId);
            jsonBody.put("course", courseArray);
            jsonBody.put("name", phoneNumber);

            JSONObject watermark = new JSONObject();
            JSONArray textsArray = new JSONArray();
            JSONObject textObj = new JSONObject();
            textObj.put("text", phoneNumber);
            textsArray.put(textObj);
            watermark.put("texts", textsArray);

            jsonBody.put("watermark", watermark);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = RequestBody.create(mediaType, jsonBody.toString());

        Request request = new Request.Builder()
                .url("https://panel.spotplayer.ir/license/edit/")
                .method("POST", body)
                .addHeader("$API", API_KEY)
                .addHeader("$LEVEL", "-1")
                .addHeader("Content-Type", "application/json")
                .build();


        ResponseModel responseModel = null;
        try {
            Response response = client.newCall(request).execute();
            Gson gson = new GsonBuilder().create();
            responseModel = gson.fromJson(response.body().string(), ResponseModel.class);
            // Handle response here
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseModel;


    }

}
