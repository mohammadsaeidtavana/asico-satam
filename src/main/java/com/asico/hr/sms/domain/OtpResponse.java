package com.asico.hr.sms.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpResponse {


    @SerializedName("result")
    @JsonProperty("result")
    Result result;


    @SerializedName("items")
    @JsonProperty("items")
    List<Integer> items;
}
