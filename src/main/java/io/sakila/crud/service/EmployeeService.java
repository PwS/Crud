package io.sakila.crud.service;

import io.sakila.crud.model.EmployeeDTO;
import io.sakila.crud.model.request.employee.EmployeeRequestDTO;
import io.sakila.crud.model.request.employee.EmployeeRequestDeleteDTO;
import io.sakila.crud.model.request.employee.EmployeeRequestUpdateDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> findAll();

    EmployeeDTO findById(Integer employeeId) throws Exception;

    EmployeeDTO addEmployee(EmployeeRequestDTO valueForCreate) throws Exception;

    EmployeeDTO updateEmployeeById(EmployeeRequestUpdateDTO valueForUpdate) throws Exception;

    String deleteEmployeeById(EmployeeRequestDeleteDTO employeeRequestDeleteDTO);
}
