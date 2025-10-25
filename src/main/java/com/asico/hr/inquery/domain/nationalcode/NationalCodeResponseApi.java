package com.asico.hr.inquery.domain.nationalcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NationalCodeResponseApi {

    @JsonProperty("data")
    private com.asico.hr.inquery.domain.nationalcode.Data data;
    @JsonProperty("message")
    private String message;

    @JsonProperty("isSuccess")
    private boolean isSuccess;







}
