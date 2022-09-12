package io.sakila.crud.service.impl;

import io.sakila.crud.entity.employee.Employee;
import io.sakila.crud.entity.employee.Salaries;
import io.sakila.crud.entity.employee.Titles;
import io.sakila.crud.model.EmployeeDTO;
import io.sakila.crud.model.request.employee.EmployeeRequestDTO;
import io.sakila.crud.model.request.employee.EmployeeRequestDeleteDTO;
import io.sakila.crud.model.request.employee.EmployeeRequestUpdateDTO;
import io.sakila.crud.model.request.employee.salary.SalaryRequestDTO;
import io.sakila.crud.model.request.employee.title.TitlesRequestDTO;
import io.sakila.crud.repository.EmployeeRepository;
import io.sakila.crud.service.EmployeeService;
import io.sakila.crud.util.ConstantValue;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EmployeeDTO> findAll() {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAll();

        for (Employee employee : employees) {
            if (Boolean.TRUE.equals(employee.getActive())) {
                employeeDTOS.add(modelMapper.map(employee, EmployeeDTO.class));
            }
        }

        return employeeDTOS;
    }

    @Transactional
    @Override
    public EmployeeDTO addEmployee(EmployeeRequestDTO valueForCreate) throws Exception {

        valueForCreate.setActive(true);
        valueForCreate.setCreatedDate(ConstantValue.DEFAULT_DATE_NOW);
        valueForCreate.setCreatedBy(ConstantValue.DEFAULT_CREATED_BY);

        SalaryRequestDTO salaryDTO = new SalaryRequestDTO();
        salaryDTO.setSalaryValue(valueForCreate.getSalary().getSalaryValue());
        salaryDTO.setActive(true);
        salaryDTO.setCreatedDate(ConstantValue.DEFAULT_DATE_NOW);
        salaryDTO.setCreatedBy(ConstantValue.DEFAULT_CREATED_BY);

        List<TitlesRequestDTO> titlesRequestDTOS = new ArrayList<>();

        for (TitlesRequestDTO titlesRequestDTO : valueForCreate.getTitles()) {
            TitlesRequestDTO titlesRequestDTO1 = new TitlesRequestDTO();
            titlesRequestDTO1.setTitleValue(titlesRequestDTO.getTitleValue());
            titlesRequestDTO1.setActive(true);
            titlesRequestDTO1.setCreatedDate(ConstantValue.DEFAULT_DATE_NOW);
            titlesRequestDTO1.setCreatedBy(ConstantValue.DEFAULT_CREATED_BY);
            titlesRequestDTOS.add(titlesRequestDTO1);
        }

        valueForCreate.setSalary(salaryDTO);
        valueForCreate.setTitles(titlesRequestDTOS);


        try {
            Employee employeeEntity = modelMapper.map(valueForCreate, Employee.class);


            Employee storedEmployeeEntity = employeeRepository.save(employeeEntity);

            return modelMapper.map(storedEmployeeEntity, EmployeeDTO.class);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public EmployeeDTO findById(Integer employeeId) throws Exception {

        try {
            Optional<Employee> resultFindById = employeeRepository.findById(employeeId);


            if (!resultFindById.isPresent()) {
                return null;
            }

            if (Boolean.TRUE.equals(resultFindById.get().getActive())) {
                return resultFindById.map(employee -> modelMapper.map(employee, EmployeeDTO.class)).orElse(null);
            }

            return null;
        } catch (Exception e) {
            throw new Exception(employeeId + "Not Found");
        }
    }

    @Transactional
    @Override
    public EmployeeDTO updateEmployeeById(EmployeeRequestUpdateDTO valueForUpdate) throws Exception {
        try {


            Optional<Employee> employee = employeeRepository.findById(valueForUpdate.getEmployeeNo());

            if (!employee.isPresent()) {
                return null;
            }

            if (Boolean.FALSE.equals(employee.get().getActive())) {
                return null;
            }

            Employee currentValue = employee.get();

            currentValue = addAbstractAuditingValueForUpdatedEmployee(currentValue, valueForUpdate.getUpdatedBy(), true);
            currentValue.setSalary(addAbstractAuditingValueForUpdatedSalary(currentValue.getSalary(), valueForUpdate.getUpdatedBy(), true));
            currentValue.setTitles(addAbstractAuditingValueForUpdatedTitles(currentValue.getTitles(), valueForUpdate.getUpdatedBy(), true));

            currentValue.setFirstName(valueForUpdate.getFirstName());
            currentValue.setLastName(valueForUpdate.getLastName());
            currentValue.setBirthDate(valueForUpdate.getBirthDate());
            currentValue.setGender(valueForUpdate.getGender());
            currentValue.setHireDate(valueForUpdate.getHireDate());
            if (valueForUpdate.getSalaries() != null) {
                currentValue.getSalary().setSalary(valueForUpdate.getSalaries().getSalary());
            }

            if (!valueForUpdate.getTitles().isEmpty()) {
                List<Titles> convertHasToList = new ArrayList<>(currentValue.getTitles());
                List<Titles> convertDtoToEntity = Arrays.asList(modelMapper.map(valueForUpdate.getTitles(), Titles[].class));
                currentValue.setTitles(compareExistingWithNewUpdateValue(convertDtoToEntity, convertHasToList, valueForUpdate.getUpdatedBy()));
            }


            Employee storedEmployeeEntity = employeeRepository.save(currentValue);

            return modelMapper.map(storedEmployeeEntity, EmployeeDTO.class);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Transactional
    @Override
    public String deleteEmployeeById(EmployeeRequestDeleteDTO requestDeleteDTO) {
        Optional<Employee> employee = employeeRepository.findById(requestDeleteDTO.getEmployeeNo());
        if (!employee.isPresent()) {
            return requestDeleteDTO.getEmployeeNo() + "Not Found";
        }


        Employee valueForDeleted = addAbstractAuditingValueForUpdatedEmployee(employee.get(), requestDeleteDTO.getUserId(), false);
        valueForDeleted.setTitles(addAbstractAuditingValueForUpdatedTitles(valueForDeleted.getTitles(), requestDeleteDTO.getUserId(), false));
        valueForDeleted.setSalary(addAbstractAuditingValueForUpdatedSalary(valueForDeleted.getSalary(), requestDeleteDTO.getUserId(), false));

        employeeRepository.save(valueForDeleted);


        return requestDeleteDTO.getEmployeeNo().toString();

    }

    private List<Titles> compareExistingWithNewUpdateValue(List<Titles> req, List<Titles> exst, String userId) {
        List<Titles> result = new ArrayList<>();

        for (Titles titlesReq : req) {
            Titles titlesRequest = exst.stream().filter(f1 -> titlesReq.getTitlesId() == f1.getTitlesId()).filter(Titles::getActive).findFirst().orElse(null);

            if (titlesRequest == null) {
                Titles titlesNew = new Titles();
                titlesNew.setTitleValue(titlesReq.getTitleValue());
                titlesNew.setActive(true);
                titlesNew.setCreatedDate(ConstantValue.DEFAULT_DATE_NOW);
                titlesNew.setCreatedBy(ConstantValue.DEFAULT_CREATED_BY);
                result.add(titlesNew);
            } else {
                titlesRequest.setTitleValue(titlesReq.getTitleValue());
                titlesRequest.setLastModifiedBy(userId);
                titlesRequest.setCreatedDate(ConstantValue.DEFAULT_DATE_NOW);
                result.add(titlesRequest);
            }
        }

        for (Titles titlesExst : exst) {
            Titles titlesExisting = result.stream().filter(f1 -> titlesExst.getTitlesId() == f1.getTitlesId()).filter(
                    Titles::getActive
            ).findFirst().orElse(null);

            if (titlesExisting == null && Boolean.TRUE.equals(titlesExst.getActive())) {
                result.add(titlesExst);
            }
        }
        return result;
    }

    private Employee addAbstractAuditingValueForUpdatedEmployee(Employee employee, String userId, boolean active) {
        Employee result = employee;
        /**
         * Set Value Abstract Auditing Entity
         */
        result.setActive(active);
        result.setLastModifiedDate(ConstantValue.DEFAULT_DATE_NOW);
        result.setLastModifiedBy(userId);

        return result;

    }

    private Salaries addAbstractAuditingValueForUpdatedSalary(Salaries updatedValue, String userId, boolean active) {
        Salaries result = updatedValue;
        if (result == null) {
            return null;
        }
        /**
         * Set Value Abstract Auditing Entity
         */
        result.setActive(active);
        result.setLastModifiedDate(ConstantValue.DEFAULT_DATE_NOW);
        result.setLastModifiedBy(userId);

        return result;
    }

    private List<Titles> addAbstractAuditingValueForUpdatedTitles(List<Titles> updateValue, String userId, boolean active) {
        List<Titles> result = updateValue;
        if (updateValue == null || updateValue.isEmpty()) {
            return new ArrayList<>();
        }

        for (Titles title : updateValue) {
            /**
             * Set Value Abstract Auditing Entity
             */
            title.setActive(active);
            title.setLastModifiedDate(ConstantValue.DEFAULT_DATE_NOW);
            title.setLastModifiedBy(userId);
            result.add(title);
        }
        return result;
    }

}
