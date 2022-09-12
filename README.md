## Cryd
Example For Crud Structure EmployeeDepartment

## File or Application needed
* [XAMPP](https://www.apachefriends.org/download.html) - MySql Local
* [Chocolatey](https://chocolatey.org/) - Package Manager

## Structure Tree
```bash
├───main
│   ├───java
│   │   └───io
│   │       └───sakila
│   │           └───crud
│   │               ├───config
│   │               ├───controller
│   │               ├───entity
│   │               │   ├───department
│   │               │   └───employee
│   │               ├───exception
│   │               ├───model
│   │               │   └───request
│   │               │       ├───department
│   │               │       └───employee
│   │               │           ├───salary
│   │               │           └───title
│   │               ├───repository
│   │               ├───service
│   │               │   └───impl
│   │               └───util
│   └───resources
│       └───liquibase
│           └───changelog
│               └───sql
│                   ├───alter_table
│                   └───create_table
└───test
    └───java
        └───io
            └───sakila
                └───crud
                    ├───controller
                    │   ├───department
                    │   └───employee
                    ├───service
                    │   ├───department
                    │   └───employee
                    └───util
```

## How To Use
Cloning Manual
```
git clone https://github.com/PwS/Crud.git
```

# Using IDE 

Open Using Your IDE (For This Example Using IntelliJ)

1. Welcoming Screen -> Click On Get From VCS
<img src="images/WelcomeScreen.PNG" alt="drawing" width="800"/>

2. Adjustment Url Which 1 For Url and 2 For Path Directory (Local Machine)
<img src="images/GetFromUrl.PNG" alt="drawing" width="800"/>

# PreDefine Connection
1. For THis Example Using XAMPP (Run the Apache & My SQL)
<img src="images/Start MySql on XAMPP.PNG" alt="drawing" width="800"/>
2. After Successfuly Running Go To [http://localhost/phpmyadmin/](http://localhost/phpmyadmin/) and create new database , naming as you wish
   <img src="images/Create New Database.PNG" alt="drawing" width="800"/>

# Setup
1. Back to the Project , Configure the connection
   <img src="images/ConfigureConnection.PNG" alt="drawing" width="800"/>
Make Adjustment on , Configure your database , username and password (default xampp username is root with no password)
```
spring.datasource.url=jdbc:mysql://localhost:3306/sakila
spring.datasource.username=root
spring.datasource.password=
```
2. Get The DDL Script on ***\Crud\src\main\resources\liquibase\changelog\sql**
   <img src="images/DDL Located.PNG" alt="drawing" width="800"/>
3. Execute the DDL On Database IDE or http://localhost/phpmyadmin/
4. Simply Click Running on MenuBar (**1** for Running and **2** for Debug)
<img src="images/MenuBar.PNG" alt="drawing" width="800"/>
5. Successfuly Started
   <img src="images/Started.PNG" alt="drawing" width="800"/>

# Documentation
1. After Succesfully Started , API Documentation Can be access on 
```
http://localhost:5005/sakila_crud/main/swagger-ui/index.html
```
   <img src="images/Swagger.PNG" alt="drawing" width="800"/>

2. Or Can be access through this postman Collection Located in
```
*\Crud\postman_collection\
```

# Import Postman
<img src="images/postman/Import.PNG" alt="drawing" width="800"/>
<img src="images/postman/Imported.PNG" alt="drawing" width="800"/>
<img src="images/postman/Postman Opened.png" alt="drawing" width="800"/>




