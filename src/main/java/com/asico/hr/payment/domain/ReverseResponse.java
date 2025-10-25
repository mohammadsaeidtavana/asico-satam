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
public class ReverseResponse implements Serializable {
    @JsonProperty("data")
    private data data;

    @JsonProperty("errors")
    private List<String> errors;

    @Data
    public static class data {
        @JsonProperty("card_hash")
        private String card_hash;

        @JsonProperty("card_pan")
        private String card_pan;

        @JsonProperty("ref_id")
        private String ref_id;

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
