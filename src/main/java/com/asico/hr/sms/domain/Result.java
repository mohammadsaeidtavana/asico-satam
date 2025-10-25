package com.asico.hr.sms.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {


    @JsonProperty("code")
    private int code;

    @JsonProperty("message")
    private String message;
}
