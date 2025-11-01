package com.asico.hr.domain.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
@Data
public class Education {

    @JsonProperty("degree")
    private String degree;
    @JsonProperty("field")
    private String field;
    @JsonProperty("university")
    private String university;
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("endDate")
    private String endDate;
    @JsonProperty("file")
    private MultipartFile file; // برای فایل دانشنامه


}
