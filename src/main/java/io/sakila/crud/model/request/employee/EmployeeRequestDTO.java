package io.sakila.crud.model.request.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sakila.crud.model.SalaryDTO;
import io.sakila.crud.model.TitlesDTO;
import io.sakila.crud.model.request.employee.salary.SalaryRequestDTO;
import io.sakila.crud.model.request.employee.title.TitlesRequestDTO;
import io.sakila.crud.util.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class EmployeeRequestDTO {

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("birthDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("hireDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;

    @JsonProperty("salary")
    private SalaryRequestDTO salary;

    @JsonProperty("titles")
    private List<TitlesRequestDTO> titles;

}
