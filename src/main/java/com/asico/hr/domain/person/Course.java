package com.asico.hr.domain.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class Course {

    @JsonProperty("title")
    private String title;
    @JsonProperty("organizer")
    private String organizer;
    @JsonProperty("receivedDate")
    private LocalDate receivedDate;
    @JsonProperty("duration")
    private String duration;
    @JsonProperty("file")
    private MultipartFile file; // فایل اختیاری
}
