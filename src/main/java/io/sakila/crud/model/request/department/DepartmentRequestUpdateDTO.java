package io.sakila.crud.model.request.department;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentRequestUpdateDTO {

    @JsonProperty("departmentId")
    private String departmentId;

    @JsonProperty("departmentName")
    private String departmentName;

    @JsonProperty("updatedBy")
    private String updatedBy;
}
