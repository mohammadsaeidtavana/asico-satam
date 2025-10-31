package com.asico.hr.domain.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Experience {
    @JsonProperty("company")
    private String company;
    @JsonProperty("city")
    private String city;
    @JsonProperty("startDate")
    private LocalDate startDate;
    @JsonProperty("endDate")
    private LocalDate endDate;
    @JsonProperty("duration")
    private String duration;
    @JsonProperty("position")
    private String position;
}
