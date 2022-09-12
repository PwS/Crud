package io.sakila.crud.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sakila.crud.util.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDTO {

    @JsonProperty("employeeNo")
    private int empNo;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("birthDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @JsonProperty("hireDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;

    @JsonProperty("salary")
    private SalaryDTO salaries;

    @JsonProperty("titles")
    private List<TitlesDTO> titles;
}
