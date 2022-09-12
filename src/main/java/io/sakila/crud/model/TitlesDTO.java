package io.sakila.crud.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TitlesDTO {
    @JsonProperty("titlesId")
    private int titlesId;
    @JsonProperty("titleValue")
    private String title;
}
