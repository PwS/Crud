package io.sakila.crud.entity.employee;

import io.sakila.crud.entity.AbstractAuditingEntity;
import io.sakila.crud.util.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
public class Employee extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_no")
    private int empNo;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition = "ENUM(M,F)")
    private Gender gender;

    @Column(name = "hire_date", columnDefinition = "DATETIME")
    private Date hireDate;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Salaries salary;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Titles> titles;

    public Employee(String firstName, String lastName, Date birthDate, Gender gender, Date hireDate, Salaries salary, List<Titles> titles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.hireDate = hireDate;
        this.salary = salary;
        this.titles = titles;
    }

    public void setTitles(List<Titles> titles) {
        this.titles = titles;
        titles.forEach(entity -> entity.setEmployee(this));
    }


}
