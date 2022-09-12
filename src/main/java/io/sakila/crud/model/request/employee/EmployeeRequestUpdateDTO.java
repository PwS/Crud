package io.sakila.crud.model.request.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sakila.crud.model.SalaryDTO;
import io.sakila.crud.model.TitlesDTO;
import io.sakila.crud.util.Gender;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class EmployeeRequestUpdateDTO {
    @JsonProperty("employeeNo")
    private Integer employeeNo;

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

    @JsonProperty("salaries")
    private SalaryDTO salaries;

    @JsonProperty(value = "titles",defaultValue = "<TitlesDTO>[]")
    private List<TitlesDTO> titles;

    @JsonProperty("updateBy")
    private String updatedBy;
}
