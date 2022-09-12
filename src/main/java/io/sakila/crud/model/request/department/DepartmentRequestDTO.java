package io.sakila.crud.model.request.department;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentRequestDTO {
    @JsonProperty("departmentName")
    private String departmentName;
}
