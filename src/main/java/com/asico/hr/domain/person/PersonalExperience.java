package com.asico.hr.domain.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class PersonalExperience {

    @JsonProperty("companyName")
    private String companyName;          // نام شرکت
    @JsonProperty("startDate")
    private String startDate;         // تاریخ شروع تصدی
    @JsonProperty("endDate")
    private String endDate;           //
    @JsonProperty("officialGazette")// تاریخ اتمام
    private MultipartFile officialGazette; // فایل روزنامه رسمی
}
