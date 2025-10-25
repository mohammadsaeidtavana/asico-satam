package com.asico.hr.payment.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */

@Data
public class AuthResponse implements Serializable {
    @JsonProperty("data")
    private data data;

    @JsonProperty("errors")
    private List<String> errors;

    @Data
    public  static class data {

        @JsonProperty("authority")
        private String authority;

        @JsonProperty("fee")
        private int fee;

        @JsonProperty("fee_type")
        private String fee_type;

        @JsonProperty("code")
        private int code;

        @JsonProperty("message")
        private String message;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "data=" + data +
                ", errors=" + errors +
                '}';
    }
}
