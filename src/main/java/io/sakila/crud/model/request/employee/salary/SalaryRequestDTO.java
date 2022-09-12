package io.sakila.crud.model.request.employee.salary;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.sakila.crud.model.AbstractAuditingDTO;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SalaryRequestDTO extends AbstractAuditingDTO {
    @JsonProperty("salaryValue")
    private Double salaryValue;
}
