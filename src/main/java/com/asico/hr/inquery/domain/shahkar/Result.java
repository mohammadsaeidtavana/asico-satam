package com.asico.hr.inquery.domain.shahkar;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Result {

    @JsonProperty("isMatched")
    private boolean isMatched;
}
