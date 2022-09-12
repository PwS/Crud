package io.sakila.crud.service.impl;

import io.sakila.crud.entity.department.Department;
import io.sakila.crud.model.DepartmentDTO;
import io.sakila.crud.model.request.department.DepartmentRequestDTO;
import io.sakila.crud.model.request.department.DepartmentRequestUpdateDTO;
import io.sakila.crud.repository.DepartmentRepository;
import io.sakila.crud.service.DepartmentService;
import io.sakila.crud.util.ConstantValue;
import io.sakila.crud.util.IdGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    IdGenerator idGenerator;

    @Override
    public List<DepartmentDTO> findAll() {
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        List<Department> departments = departmentRepository.findAll();

        for (Department department : departments) {
            if (Boolean.TRUE.equals(department.getActive())) {
                departmentDTOS.add(modelMapper.map(department, DepartmentDTO.class));
            }
        }

        return departmentDTOS;
    }

    @Override
    public DepartmentDTO findById(String deptNo) {

        Optional<Department> resultFindById = departmentRepository.findById(deptNo);

        if (!resultFindById.isPresent()) {
            return null;
        }

        if (Boolean.TRUE.equals(resultFindById.get().getActive())) {
            return resultFindById.map(department -> modelMapper.map(department, DepartmentDTO.class)).orElse(null);
        }

        return null;

    }

    @Override
    public DepartmentDTO addDepartment(DepartmentRequestDTO valueForCreate) {
        Department departmentEntity = modelMapper.map(valueForCreate, Department.class);
        String departmentId = idGenerator.generateStringId(4);

        departmentEntity.setDepartmentId(departmentId);
        /**
         * Set Value Abstract Auditing Entity
         */
        departmentEntity.setActive(true);
        departmentEntity.setCreatedDate(ConstantValue.DEFAULT_DATE_NOW);
        departmentEntity.setCreatedBy(ConstantValue.DEFAULT_CREATED_BY);

        Department storedDepartmentEntity = departmentRepository.save(departmentEntity);

        return modelMapper.map(storedDepartmentEntity, DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO updateDepartmentById(DepartmentRequestUpdateDTO valueForUpdate) {

        Optional<Department> department = departmentRepository.findById(valueForUpdate.getDepartmentId());

        if (!department.isPresent()) {
            return null;
        }

        Department existingValue = department.get();
        Department departmentEntity = modelMapper.map(valueForUpdate, Department.class);

        departmentEntity.setDepartmentName(valueForUpdate.getDepartmentName());
        /**
         * Set Value Abstract Auditing Entity
         */
        departmentEntity.setActive(existingValue.getActive());
        departmentEntity.setCreatedDate(existingValue.getCreatedDate());
        departmentEntity.setCreatedBy(existingValue.getCreatedBy());
        departmentEntity.setLastModifiedBy(valueForUpdate.getUpdatedBy());
        departmentEntity.setLastModifiedDate(ConstantValue.DEFAULT_DATE_NOW);


        Department updatedDepartmentEntity = departmentRepository.save(departmentEntity);

        return modelMapper.map(updatedDepartmentEntity, DepartmentDTO.class);
    }

    @Override
    public String deleteDepartmentById(String deptNo, String userId) {
        Optional<Department> department = departmentRepository.findById(deptNo);

        if (!department.isPresent()) {
            return deptNo + "Not Found";
        }

        Department valueForUpdate = department.get();
        valueForUpdate.setActive(false);
        /**
         * Set Value Abstract Auditing Entity
         */
        valueForUpdate.setLastModifiedBy(userId);
        valueForUpdate.setLastModifiedDate(ConstantValue.DEFAULT_DATE_NOW);

        departmentRepository.save(valueForUpdate);

        return deptNo;
    }

}
