package io.sakila.crud.service.department;

import io.sakila.crud.entity.department.Department;
import io.sakila.crud.model.DepartmentDTO;
import io.sakila.crud.model.EmployeeDTO;
import io.sakila.crud.model.request.department.DepartmentRequestDTO;
import io.sakila.crud.repository.DepartmentRepository;
import io.sakila.crud.service.DepartmentService;
import io.sakila.crud.service.impl.DepartmentServiceImpl;
import io.sakila.crud.util.ConstantValue;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class DepartmentServiceTest {


    @InjectMocks
    DepartmentServiceImpl departmentService;

    @Mock
    DepartmentRepository departmentRepository;


    @Test
    public void getAllDepartment() {
        List<Department> list = new ArrayList<>();
        Department dept1 = new Department();
        dept1.setDepartmentId("1234");
        dept1.setDepartmentName("test");
        list.add(dept1);

        when(departmentRepository.findAll()).thenReturn(list);

        List<DepartmentDTO> result = departmentService.findAll();
        assertNotNull(result);
    }


    @Test
    public void getDepartmentById() {
        Department department = new Department();
        department.setDepartmentId("uvn6");
        department.setDepartmentName("test");

        when(departmentRepository.findById(any(String.class))).thenReturn(Optional.of(department));

        Optional<Department> result = departmentRepository.findById("uvn6");
        assertNotNull(result);
    }

    @Test
    public void addDepartment() {
        Department department = new Department();
        department.setDepartmentId("uvn6");
        department.setDepartmentName("test");
        department.setActive(true);
        department.setCreatedDate(ConstantValue.DEFAULT_DATE_NOW);
        department.setCreatedBy(ConstantValue.DEFAULT_CREATED_BY);

        when(departmentRepository.save(department)).thenReturn(department);

        Department result = departmentRepository.save(department);
        assertNotNull(result);
    }

    @Test
    public void updateDepartment() {
        Department department = new Department();
        department.setDepartmentId("uvn6");
        department.setDepartmentName("test");
        department.setActive(true);
        department.setCreatedDate(ConstantValue.DEFAULT_DATE_NOW);
        department.setCreatedBy(ConstantValue.DEFAULT_CREATED_BY);
        department.setLastModifiedBy(ConstantValue.DEFAULT_CREATED_BY);
        department.setLastModifiedDate(ConstantValue.DEFAULT_DATE_NOW);

        when(departmentRepository.save(department)).thenReturn(department);

        Department result = departmentRepository.save(department);
        assertNotNull(result);
    }


    @Test
    public void deleteDepartment() {
        Department department = new Department();
        department.setDepartmentId("uvn6");
        department.setDepartmentName("test");
        department.setLastModifiedBy(ConstantValue.DEFAULT_CREATED_BY);
        department.setLastModifiedDate(ConstantValue.DEFAULT_DATE_NOW);

        when(departmentRepository.save(department)).thenReturn(department);

        Department result = departmentRepository.save(department);
        assertNotNull(result);
    }

}
