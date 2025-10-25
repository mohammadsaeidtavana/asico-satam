package com.asico.hr.sms.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpRequest {

    @NotBlank(message = "phone number is required")
    @Length(min=11, max=11, message="phone number must be 11 digit")
    @JsonProperty("phoneNumber")
    private String phoneNumber;


    @NotBlank(message = "phone number is required")
    @Length(min=10, max=10, message="phone number must be 10 digit")
    @JsonProperty("codeMeli")
    private String codeMeli;



}
