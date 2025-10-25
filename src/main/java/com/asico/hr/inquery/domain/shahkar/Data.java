package com.asico.hr.inquery.domain.shahkar;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class Data {


    @JsonProperty("result")
    private Result result;

    @JsonProperty("trackId")
    private String trackId;
}
