package io.sakila.crud.model.request.employee.title;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TitlesRequestDTO {
    @JsonProperty("titleValue")
    private String titleValue;
}
