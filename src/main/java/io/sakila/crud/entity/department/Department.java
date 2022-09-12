package io.sakila.crud.entity.department;

import io.sakila.crud.entity.AbstractAuditingEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "departments")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Department extends AbstractAuditingEntity {

    @Id
    @Column(name = "dept_no", nullable = false)
    private String departmentId;

    @Column(name = "dept_name")
    private String departmentName;

}

