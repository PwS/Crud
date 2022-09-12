package io.sakila.crud.controller;

import io.sakila.crud.config.SwaggerConfig;
import io.sakila.crud.exception.DepartmentException;
import io.sakila.crud.model.DepartmentDTO;
import io.sakila.crud.model.GlobalResponseDTO;
import io.sakila.crud.model.request.department.DepartmentRequestDTO;
import io.sakila.crud.model.request.department.DepartmentRequestDeletedDTO;
import io.sakila.crud.model.request.department.DepartmentRequestUpdateDTO;
import io.sakila.crud.service.DepartmentService;
import io.sakila.crud.util.ConstantValue;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = SwaggerConfig.DEPARTMENT_TAG)
@RequestMapping("/api/Department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Operation(summary = "Find All Existing Department", description = "Find All Existing Department")
    @GetMapping("/findAll")
    public ResponseEntity<GlobalResponseDTO> findAllDepartment() {

        List<DepartmentDTO> result = departmentService.findAll();

        String status = ConstantValue.RESPONSE_SUCCESS;
        String message = ConstantValue.GLB_MESSAGE_NOT_FOUND;

        if (result != null) {
            message = ConstantValue.GLB_MESSAGE_FOUND;
        }
        return ResponseEntity.ok(new GlobalResponseDTO<>(status, message, result));
    }

    @Operation(summary = "Find Department By Id", description = "Find Department By id")
    @GetMapping("/findById/{deptId}")
    public ResponseEntity<GlobalResponseDTO> findByDepartmentId(@PathVariable String deptId) {
        DepartmentDTO result = departmentService.findById(deptId);
        String status = ConstantValue.RESPONSE_SUCCESS;
        String message = ConstantValue.GLB_MESSAGE_NOT_FOUND;

        if (result != null) {
            message = ConstantValue.GLB_MESSAGE_FOUND;
        }
        return ResponseEntity.ok(new GlobalResponseDTO<>(status, message, result));
    }

    @Operation(summary = "Create Department", description = "Create Department")
    @PostMapping("/createDepartment")
    public ResponseEntity<GlobalResponseDTO> createDepartment(@RequestBody DepartmentRequestDTO request) throws DepartmentException {
        if (request.getDepartmentName() == null || request.getDepartmentName().isEmpty()) {
            throw new DepartmentException("Missing Required Fields Name " + request.getDepartmentName());
        }

        DepartmentDTO createdDepartmentDTO = departmentService.addDepartment(request);

        String status = ConstantValue.RESPONSE_FAILED;
        String message = ConstantValue.FAILED_CREATE_DATA;

        if (createdDepartmentDTO != null) {
            status = ConstantValue.RESPONSE_SUCCESS;
            message = ConstantValue.SUCCESS_CREATE_DATA;
        }
        return ResponseEntity.ok(new GlobalResponseDTO<>(status, message, createdDepartmentDTO));
    }

    @Operation(summary = "Update Department", description = "Update Department")
    @PutMapping("/updateDepartment")
    public ResponseEntity<GlobalResponseDTO> updateDepartment(@RequestBody DepartmentRequestUpdateDTO request) throws DepartmentException {
        if (request.getDepartmentId() == null || request.getDepartmentId().isEmpty()) {
            throw new DepartmentException("Missing Required Fields Id " + request.getDepartmentName());
        }
        if (request.getDepartmentName() == null || request.getDepartmentName().isEmpty()) {
            throw new DepartmentException("Missing Required Fields Name " + request.getDepartmentName());
        }


        DepartmentDTO updatedDepartmentDTO = departmentService.updateDepartmentById(request);

        String status = ConstantValue.RESPONSE_FAILED;
        String message = ConstantValue.FAILED_UPDATE_DATA;

        if (updatedDepartmentDTO != null) {
            status = ConstantValue.RESPONSE_SUCCESS;
            message = ConstantValue.SUCCESS_UPDATE_DATA;
        }
        return ResponseEntity.ok(new GlobalResponseDTO<>(status, message, updatedDepartmentDTO));
    }

    @Operation(summary = "Delete Department", description = "Delete Department")
    @DeleteMapping("/deleteDepartment")
    public ResponseEntity<GlobalResponseDTO> deleteDepartment(@RequestBody DepartmentRequestDeletedDTO request) {
        if (request.getDepartmentId() == null) {
            throw new DepartmentException("Missing Value Required Fields Id " + request.getDepartmentId());
        }
        if (request.getUpdatedBy() == null) {
            throw new DepartmentException("Missing Value Required Fields Updated By " + request.getUpdatedBy());
        }

        String statusDeleted = departmentService.deleteDepartmentById(request.getDepartmentId(), request.getUpdatedBy());

        String status = ConstantValue.RESPONSE_FAILED;
        String message = ConstantValue.FAILED_DELETE_DATA;

        if (!statusDeleted.contains("Not Found")) {
            status = ConstantValue.RESPONSE_SUCCESS;
            message = ConstantValue.SUCCESS_DELETE_DATA;
        }
        return ResponseEntity.ok(new GlobalResponseDTO<>(status, message, "Department Id " + request.getDepartmentId()));
    }


}
