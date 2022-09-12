package io.sakila.crud.model.request.employee.salary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SalaryRequestDTO {
    @JsonProperty("salaryValue")
    private Double salaryValue;
}
