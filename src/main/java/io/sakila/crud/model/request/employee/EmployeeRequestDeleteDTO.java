package io.sakila.crud.model.request.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDeleteDTO {

    @JsonProperty("employeeId")
    private Integer employeeNo;

    @JsonProperty("userId")
    private String userId;

}
