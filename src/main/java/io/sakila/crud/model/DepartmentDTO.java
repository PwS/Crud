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
public class DepartmentDTO {
    @JsonProperty("departmentId")
    private String departmentId;

    @JsonProperty("departmentName")
    private String departmentName;
}
