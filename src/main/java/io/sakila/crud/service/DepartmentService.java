package io.sakila.crud.service;

import io.sakila.crud.model.DepartmentDTO;
import io.sakila.crud.model.request.department.DepartmentRequestDTO;
import io.sakila.crud.model.request.department.DepartmentRequestUpdateDTO;

import java.util.List;

public interface DepartmentService {

    List<DepartmentDTO> findAll();

    DepartmentDTO findById(String deptNo);

    DepartmentDTO addDepartment(DepartmentRequestDTO valueForCreate);

    DepartmentDTO updateDepartmentById(DepartmentRequestUpdateDTO valueForUpdate);

    String deleteDepartmentById(String deptNo,String userId);


}
