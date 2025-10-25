package com.asico.hr.inquery.domain.nationalcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NationalCodeRequestApi {

    @JsonProperty("nationalCode")
   private String nationalCode;
}
