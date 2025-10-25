package com.asico.hr.inquery.domain.shahkar;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ShahkarResponseApi {

    @JsonProperty("data")
    private com.asico.hr.inquery.domain.shahkar.Data data;
    @JsonProperty("message")
    private String message;

    @JsonProperty("isSuccess")
    private boolean isSuccess;







}
