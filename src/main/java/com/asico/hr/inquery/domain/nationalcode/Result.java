package com.asico.hr.inquery.domain.nationalcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Result {

    @JsonProperty("isValid")
    private boolean isValid;
}
