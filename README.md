  Spring.io based REST services

#### Setup

```bash
# code
$ git clone https://github.com/partharamanujam/spring-rest.git
# build + run
$ cd spring-rest/starter-project
$ ./gradlew run
```

## Features

  * [Spring Framework](https://spring.io/)
  * [Log4J Logging](http://logging.apache.org/log4j/)
  * [Gradle Builds](http://www.gradle.org/)
  * [Hibernate ORM](https://docs.jboss.org/hibernate/orm/3.6/reference/en-US/html/)
  * [C3P0 Connection-pooling](http://www.mchange.com/projects/c3p0/)
  * [MySQL DB](http://www.mysql.com/)
  * [Spring Security](http://docs.spring.io/autorepo/docs/spring-security/3.2.4.RELEASE/reference/htmlsingle)
  * [CSRF Headers](http://docs.spring.io/autorepo/docs/spring-security/3.2.4.RELEASE/reference/htmlsingle/#csrf-using) for AJAX
  * [Tomcat Deployment](http://tomcat.apache.org/) via war-file
  * Supports cmd-line builds, and multiple IDEs (STS/Eclipse, IDEA, etc.)
  * Supports content-negotiation for both JSON & XML

#### Installation Dependencies

  * MYSQL Ver 14.14 Distrib 5.6.17
  * Tomcat 7.0.52.0 (Optional)
  * Eclipse STS-3.5.1.RELEASE (Optional)

#### MYSQL Setup

  After installing the dependencies, create the following in MYSQL:
  * Create User: "testuser" with Password: "testpwd"
  * Create DB: TestDB
  * Grant necessary permissions

#### Dependency Module Versions

  View [build.gradle](starter-project/build.gradle) file for versions of dependencies. 

## Philosophy

  Spring.io based REST-API implementation integrated with commonly used components used in
  any serious development. Can be used as a starting point to get going. 


## Builds & Configuration

  There are many aspects that can be configured or used differently depending on the needs.
  This section lists some of these important aspects.

#### Gradle tasks

  The build-tool gradle is invoked using gradlew which is a shell-script in unix-like
  environments, and a batch-file on MS-Windows. It supports multiple tasks, and some
  of the useful ones are:

```bash
$ ./gradlew clean
$ ./gradlew jar
$ ./gradlew war
$ ./gradlew build # all targets
$ ./gradlew run
$ ./gradlew eclipse # IDE setup for STS/Eclipse
```

#### STS (Eclipse IDE)

  If you want to edit & debug the project within the comforts of Eclipse IDE, you will need
  to download Spring Tool Suite (tested on STS-3.5.1.RELEASE). After setting up STS:
  * run "./gradlew eclipse" from within project-folder
  * "Import" in Eclipse/STS "Existing Projects into Workspace"
  * "Browse" for project-folder, and select "root" directory
  * "Select" listed project, and press "Finish" button
  * Build & Run project within STS/Eclipse

#### Command-line

```bash
$ cd spring-rest/starter-project
$ ./gradlew run
```

#### WAR file

  On successful build, the war-file for deployment in Tomcat is available in "build/libs" directory.

#### Log4J Configuration

  See properties setup in [log4j.properties](starter-project/src/main/resources/log4j.properties) file.
  You can also set logger-level per package and/or class in the properties file.

#### Content Negotiation

  Support is available for JSON & XML contents. These are checked in the following order:
  * path extension suffix to URL, e.g., http://localhost:8080/greeting.xml?name=partha
  * format URL parameter, e.g., http://localhost:8080/greeting.xml?name=partha&format=xml
  * accept headers, e.g., application/json or application/xml
  * defaults to JSON

#### HTTP Request Logging

  All HTTP requests are logged using Log4j logger. You can enable/disable this via the [log4j.properties](starter-project/src/main/resources/log4j.properties) file.

#### Global Settings

  The supported build settings are available in [GlobalSettings.java](starter-project/src/main/java/com/starter/config/GlobalSettings.java) file. Some of the defaults are as follows:
  * Authentication : true
  * CSRF : false
  * CSRF-headers : false

## Hibernate Setup

  For Spring hibernate configuration, see [hibernate.cfg.xml](starter-project/src/main/resources/hibernate.cfg.xml)
  file. It uses the thread session context. Some simple utilities are available as a part of the setup.

#### CRUD interfaces for Hibernate POJO Entities

  Simple CRUD operations are supported via the [HibernatePojoEntity](starter-project/src/main/java/com/starter/utils/HibernatePojoEntity.java) class. CRUD operations can be performed on
  a user-entity on the following URL using the mentioned HTTP methods:
  * CREATE: POST http://localhost:8080/user
  * READ: GET http://localhost:8080/user/{uid}
  * UPDATE: PUT http://localhost:8080/user
  * DELETE: DELETE http://localhost:8080/user/{uid}

  See the following files for usage details:
  * [User.java](starter-project/src/main/java/com/starter/model/User.java) - Hibernate POJO Entity Declaration
  * [UserService.java](starter-project/src/main/java/com/starter/service/UserService.java) - CRUD interfaces using HibernatePojoEntity
  * [UserController.java](starter-project/src/main/java/com/starter/controller/UserController.java) - REST API interface

## Security

#### Authetication

  Custom-authentication setup is provided by [CustomAuthenticationProvider.java](starter-project/src/main/java/com/starter/config/CustomAuthenticationProvider.java) class. You can login in from http://localhost:8080/login screen. Following are the default users (passwords are same as user names) on the system created by MySQL
  setup script [mysqldb.sql](utils/mysqldb.sql):
  * admin
  * super
  * john
  * jane

## License

  [MIT](LICENSE)
