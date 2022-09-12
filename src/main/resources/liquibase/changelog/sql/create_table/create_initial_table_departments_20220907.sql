CREATE TABLE departments
(
    dept_no            CHAR(4),
    dept_name          VARCHAR(40),
    active             BOOLEAN     not null,
    created_by         varchar(50) not null,
    created_date       DATETIME    not null,
    last_modified_by   varchar(50),
    last_modified_date DATETIME,
    PRIMARY KEY (dept_no),
    INDEX (dept_no, dept_name)
);