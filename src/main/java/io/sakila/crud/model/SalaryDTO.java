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
public class SalaryDTO {
    @JsonProperty("salaryId")
    private int salaryId;
    @JsonProperty("salaryValue")
    private Double salary;
}
