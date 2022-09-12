package io.sakila.crud.repository;

import io.sakila.crud.entity.employee.Titles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<Titles, String> {
}
