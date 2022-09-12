package io.sakila.crud.entity.employee;


import com.fasterxml.jackson.annotation.JsonBackReference;
import io.sakila.crud.entity.AbstractAuditingEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "titles")
@Getter
@Setter
@NoArgsConstructor
public class Titles extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "titles_id")
    private int titlesId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    private Employee employee;

    @JsonBackReference
    @Column(name = "title")
    private String titleValue;

    public Titles(String titleValue) {
        this.titleValue = titleValue;
    }

}
