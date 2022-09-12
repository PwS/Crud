package io.sakila.crud.repository;

import io.sakila.crud.entity.employee.Salaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salaries,String> {
}
