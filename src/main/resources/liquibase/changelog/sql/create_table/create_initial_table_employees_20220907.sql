CREATE TABLE employees
(
    emp_no             INT(11)     NOT NULL AUTO_INCREMENT,
    birth_date         DATE,
    first_name         VARCHAR(14),
    last_name          VARCHAR(16),
    gender             ENUM ('M','F'),
    hire_date          DATE,
    active             BOOLEAN     not null,
    created_by         varchar(50) not null,
    created_date       DATETIME    not null,
    last_modified_by   varchar(50),
    last_modified_date DATETIME,
    PRIMARY KEY (emp_no),
    INDEX (emp_no)
);