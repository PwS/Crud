package io.sakila.crud.entity.employee;


import io.sakila.crud.entity.AbstractAuditingEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "salaries")
@Getter
@Setter
@NoArgsConstructor
public class Salaries extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salary_id")
    private int salaryId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    private Employee employee;

    @Column(name = "salary")
    private Double salary;

    public Salaries(Double salary) {
        this.salary = salary;
    }

}

