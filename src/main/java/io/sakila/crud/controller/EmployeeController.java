package io.sakila.crud.controller;

import io.sakila.crud.config.SwaggerConfig;
import io.sakila.crud.exception.EmployeeException;
import io.sakila.crud.model.EmployeeDTO;
import io.sakila.crud.model.GlobalResponseDTO;
import io.sakila.crud.model.request.employee.EmployeeRequestDTO;
import io.sakila.crud.model.request.employee.EmployeeRequestDeleteDTO;
import io.sakila.crud.model.request.employee.EmployeeRequestUpdateDTO;
import io.sakila.crud.service.EmployeeService;
import io.sakila.crud.util.ConstantValue;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = SwaggerConfig.EMPLOYEE_TAG)
@RequestMapping("/api/Employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Find All Existing Employee", description = "Find All Existing Employee")
    @GetMapping("/findAll")
    public ResponseEntity<GlobalResponseDTO> findAllEmployee() {

        List<EmployeeDTO> result = employeeService.findAll();

        String status = ConstantValue.RESPONSE_SUCCESS;
        String message = ConstantValue.GLB_MESSAGE_NOT_FOUND;

        if (result != null) {
            message = ConstantValue.GLB_MESSAGE_FOUND;
        }
        return ResponseEntity.ok(new GlobalResponseDTO<>(status, message, result));
    }

    @Operation(summary = "Find Employee By Id", description = "Find Employee By id")
    @GetMapping("/findById/{employeeId}")
    public ResponseEntity<GlobalResponseDTO> findByEmployeeId(@PathVariable Integer employeeId) throws Exception {
        EmployeeDTO result = employeeService.findById(employeeId);
        String status = ConstantValue.RESPONSE_SUCCESS;
        String message = ConstantValue.GLB_MESSAGE_NOT_FOUND;

        if (result != null) {
            message = ConstantValue.GLB_MESSAGE_FOUND;
        }
        return ResponseEntity.ok(new GlobalResponseDTO<>(status, message, result));
    }

    @Operation(summary = "Create Employee", description = "Create Employee")
    @PostMapping("/createEmployee")
    public ResponseEntity<GlobalResponseDTO> createEmployee(@RequestBody EmployeeRequestDTO request) throws Exception {
        if (request.getFirstName() == null || request.getFirstName().isEmpty()) {
            throw new EmployeeException("Missing Required Fields Name " + request.getFirstName());
        }

        EmployeeDTO createdEmployeeDTO = employeeService.addEmployee(request);

        String status = ConstantValue.RESPONSE_FAILED;
        String message = ConstantValue.FAILED_CREATE_DATA;

        if (createdEmployeeDTO != null) {
            status = ConstantValue.RESPONSE_SUCCESS;
            message = ConstantValue.SUCCESS_CREATE_DATA;
        }
        return ResponseEntity.ok(new GlobalResponseDTO<>(status, message, createdEmployeeDTO));
    }

    @Operation(summary = "Update Employee", description = "Update Employee")
    @PutMapping("/updateEmployee")
    public ResponseEntity<GlobalResponseDTO> updateEmployee(@RequestBody EmployeeRequestUpdateDTO request) throws Exception {
        if (request.getEmployeeNo() == null) {
            throw new EmployeeException("Missing Required Fields Id " + request.getEmployeeNo());
        }

        EmployeeDTO updatedEmployeeDTO = employeeService.updateEmployeeById(request);

        String status = ConstantValue.RESPONSE_FAILED;
        String message = ConstantValue.FAILED_UPDATE_DATA;

        if (updatedEmployeeDTO != null) {
            status = ConstantValue.RESPONSE_SUCCESS;
            message = ConstantValue.SUCCESS_UPDATE_DATA;
        }
        return ResponseEntity.ok(new GlobalResponseDTO<>(status, message, updatedEmployeeDTO));
    }

    @Operation(summary = "Delete Employee", description = "Delete Employee")
    @DeleteMapping("/deleteEmployee/")
    public ResponseEntity<GlobalResponseDTO> deleteEmployee(@RequestBody EmployeeRequestDeleteDTO request) {
        if (request.getEmployeeNo() == null) {
            throw new EmployeeException("Missing Value Required Fields Id " + request.getEmployeeNo());
        }
        if (request.getUserId() == null) {
            throw new EmployeeException("Missing Value Required Fields Updated By " + request.getUserId());
        }

        String statusDeleted = employeeService.deleteEmployeeById(request);

        String status = ConstantValue.RESPONSE_FAILED;
        String message = ConstantValue.FAILED_DELETE_DATA;

        if (!statusDeleted.contains("Not Found")) {
            status = ConstantValue.RESPONSE_SUCCESS;
            message = ConstantValue.SUCCESS_DELETE_DATA;
        }
        return ResponseEntity.ok(new GlobalResponseDTO<>(status, message, "Employee Id " + request.getEmployeeNo()));
    }


}
