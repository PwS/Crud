package io.sakila.crud.model.request.employee.title;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.sakila.crud.model.AbstractAuditingDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TitlesRequestDTO extends AbstractAuditingDTO {
    @JsonProperty("titleValue")
    private String titleValue;
}
