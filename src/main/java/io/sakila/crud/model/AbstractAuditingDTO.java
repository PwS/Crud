package io.sakila.crud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class AbstractAuditingDTO {
    @JsonIgnore
    private Boolean active;

    @JsonIgnore
    private String createdBy;

    @JsonIgnore
    private Date createdDate;

    @JsonIgnore
    private Date lastModifiedDate;

    @JsonIgnore
    private String lastModifiedBy;
}