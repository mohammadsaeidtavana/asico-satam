package com.asico.hr.domain.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Expertise {

    @JsonProperty("field")
    private String field;
    @JsonProperty("industry")// حوزه فعالیت
    private String industry;// حوزه صنعت
    @JsonProperty("orientation")
    private Integer orientation; // مدت زمان فعالیت (ماه)
}
