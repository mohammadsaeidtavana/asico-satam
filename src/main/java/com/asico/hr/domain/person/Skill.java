package com.asico.hr.domain.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Skill {

    @JsonProperty("technical")
    private String technical; // مهارت تخصصی
    @JsonProperty("soft")
    private String soft;
}
