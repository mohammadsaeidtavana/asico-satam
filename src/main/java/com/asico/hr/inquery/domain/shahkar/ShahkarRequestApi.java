package com.asico.hr.inquery.domain.shahkar;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ShahkarRequestApi {

    @JsonProperty("nationalCode")
    private String nationalCode;

    @JsonProperty("mobileNumber")
    private String mobileNumber;
}
