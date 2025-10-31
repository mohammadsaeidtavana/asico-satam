package com.asico.hr.domain.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Research {

    @JsonProperty("title")
    private String title;
    @JsonProperty("objective")// عنوان پژوهش/مقاله
    private String objective;
    @JsonProperty("link")// هدف پژوهش
    private String link;
}
