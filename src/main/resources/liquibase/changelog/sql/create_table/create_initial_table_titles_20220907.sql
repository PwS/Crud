CREATE TABLE titles
(
    titles_id          INT(11)     NOT NULL AUTO_INCREMENT,
    emp_no             INT(11),
    title              VARCHAR(50),
    active             BOOLEAN     not null,
    created_by         varchar(50) not null,
    created_date       DATETIME    not null,
    last_modified_by   varchar(50),
    last_modified_date DATETIME,
    PRIMARY KEY (titles_id),
    INDEX (titles_id, emp_no)
);